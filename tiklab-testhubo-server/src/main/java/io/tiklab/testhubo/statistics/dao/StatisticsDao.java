package io.tiklab.testhubo.statistics.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.testhubo.statistics.model.CaseStatisticsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 环境 数据访问
 */
@Repository
public class StatisticsDao {

    private static Logger logger = LoggerFactory.getLogger(StatisticsDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 获取所有状态（去重）
     * @return
     */
    public List<Map<String, Object>> getStatusList() {
        String sql = "SELECT s.name, COUNT(p.id) AS count " +
                "FROM postin_apix p " +
                "JOIN postin_api_status s ON p.status_id = s.id " +
                "GROUP BY s.name";


        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(sql);

        return maps;
    }

    /**
     * 获取新建的用例数量
     * @param caseStatisticsModel
     * @return
     */
    public List<Map<String, Object>> getNewCreateCaseStatistics(CaseStatisticsModel caseStatisticsModel) {
        Date startTime = caseStatisticsModel.getStartTime();
        Date endTime = caseStatisticsModel.getEndTime();
        String repositoryId = caseStatisticsModel.getRepositoryId();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT DATE(create_time) AS date, case_type, COUNT(*) AS count ")
                .append("FROM teston_testcase ")
                .append("WHERE create_time BETWEEN ? AND ? ");

        // 添加 repository_id 的条件
        if (repositoryId != null) {
            sqlBuilder.append("AND teston_testcase.repository_id = ? ");
        }

        sqlBuilder.append("GROUP BY DATE(create_time), case_type ")
                .append("ORDER BY DATE(create_time), case_type");

        String sql = sqlBuilder.toString();

        // 执行查询
        List<Map<String, Object>> resultList;
        if (repositoryId == null) {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime
            );
        } else {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime,
                    repositoryId
            );
        }

        return resultList;
    }

    /**
     * 获取总数和状态数
     */
    public Map<String,Object> getTotalAndStatus(CaseStatisticsModel caseStatisticsModel){
        String repositoryId = caseStatisticsModel.getRepositoryId();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("COUNT(*) AS total, ")
                .append("SUM(CASE WHEN status IS NULL OR status = 0 THEN 1 ELSE 0 END) AS notStarted, ")
                .append("SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) AS inProgress, ")
                .append("SUM(CASE WHEN status = 2 THEN 1 ELSE 0 END) AS completed ")
                .append("FROM teston_testcase ");

        // 添加 repository_id 的条件
        if (repositoryId != null) {
            sqlBuilder.append("WHERE repository_id = ? ");
        }

        String sql = sqlBuilder.toString();

        // 执行查询
        Map<String, Object> resultMap;
        if (repositoryId == null) {
            resultMap = jpaTemplate.getJdbcTemplate().queryForMap(sql);
        } else {
            resultMap = jpaTemplate.getJdbcTemplate().queryForMap(sql, repositoryId);
        }

        return resultMap;
    }


    public List<Map<String, Object>> getCaseTestResult(CaseStatisticsModel caseStatisticsModel){
        String repositoryId = caseStatisticsModel.getRepositoryId();

        String sql = "WITH LastTestInstance AS (\n" +
                "    SELECT \n" +
                "        t1.id AS testcase_id,\n" +
                "        t1.case_type,\n" +
                "        t2.status,\n" +
                "        ROW_NUMBER() OVER (PARTITION BY t1.id ORDER BY t2.create_time DESC) AS rn\n" +
                "    FROM \n" +
                "        teston_testcase t1\n" +
                "    LEFT JOIN \n" +
                "        teston_instance t2\n" +
                "    ON \n" +
                "        t1.id = t2.belong_id\n" +
                "    WHERE\n" +
                "        t1.repository_id = ? \n" +
                "\n" +
                ")\n" +
                "SELECT \n" +
                "    case_type,\n" +
                "    SUM(CASE \n" +
                "            WHEN case_type = 'api-perform' AND status = 'complete' THEN 1 \n" +
                "            WHEN case_type != 'api-perform' AND status = 'success' THEN 1 \n" +
                "            ELSE 0 \n" +
                "        END) AS pass,\n" +
                "    SUM(CASE \n" +
                "            WHEN case_type = 'api-perform' AND status != 'complete' AND status != 'start' THEN 1 \n" +
                "            WHEN case_type != 'api-perform' AND status = 'fail' THEN 1 \n" +
                "            ELSE 0 \n" +
                "        END) AS fail,\n" +
                "    SUM(CASE \n" +
                "            WHEN status IS NULL THEN 1 \n" +
                "            WHEN case_type = 'api-perform' AND status = 'start' THEN 1\n" +
                "            ELSE 0 \n" +
                "        END) AS notTested\n" +
                "FROM \n" +
                "    LastTestInstance\n" +
                "WHERE \n" +
                "    rn = 1 AND case_type != 'function'\n" +
                "GROUP BY \n" +
                "    case_type;";

        List<Map<String, Object>> resultList = jpaTemplate.getJdbcTemplate().queryForList(sql, repositoryId);

        return resultList;
    }


    public List<Map<String, Object>> getAllCaseTestResult(CaseStatisticsModel caseStatisticsModel){
        String repositoryId = caseStatisticsModel.getRepositoryId();

        String sql = "WITH LastTestInstance AS (\n" +
                "    SELECT \n" +
                "        t1.id AS testcase_id,\n" +
                "        t1.case_type,\n" +
                "        t2.status,\n" +
                "        ROW_NUMBER() OVER (PARTITION BY t1.id ORDER BY t2.create_time DESC) AS rn\n" +
                "    FROM \n" +
                "        teston_testcase t1\n" +
                "    LEFT JOIN \n" +
                "        teston_instance t2\n" +
                "    ON \n" +
                "        t1.id = t2.belong_id\n" +
                "    WHERE\n" +
                "        t1.repository_id = ?\n" +
                "),\n" +
                "TypeStatistics AS (\n" +
                "    SELECT \n" +
                "        case_type,\n" +
                "        SUM(CASE \n" +
                "                WHEN case_type = 'api-perform' AND status = 'complete' THEN 1 \n" +
                "                WHEN case_type != 'api-perform' AND status = 'success' THEN 1 \n" +
                "                ELSE 0 \n" +
                "            END) AS pass,\n" +
                "        SUM(CASE \n" +
                "                WHEN case_type = 'api-perform' AND status != 'complete' AND status != 'start' THEN 1 \n" +
                "                WHEN case_type != 'api-perform' AND status = 'fail' THEN 1 \n" +
                "                ELSE 0 \n" +
                "            END) AS fail,\n" +
                "        SUM(CASE \n" +
                "                WHEN status IS NULL THEN 1 \n" +
                "                WHEN case_type = 'api-perform' AND status = 'start' THEN 1\n" +
                "                ELSE 0 \n" +
                "            END) AS notTested\n" +
                "    FROM \n" +
                "        LastTestInstance\n" +
                "    WHERE \n" +
                "        rn = 1 AND case_type != 'function'\n" +
                "    GROUP BY \n" +
                "        case_type\n" +
                "),\n" +
                "TotalStatistics AS (\n" +
                "    SELECT\n" +
                "        'total' AS case_type,\n" +
                "        SUM(pass) AS pass,\n" +
                "        SUM(fail) AS fail,\n" +
                "        SUM(notTested) AS notTested\n" +
                "    FROM\n" +
                "        TypeStatistics\n" +
                ")\n" +
                "SELECT * FROM TypeStatistics\n" +
                "UNION ALL\n" +
                "SELECT * FROM TotalStatistics;\n";

        List<Map<String, Object>> resultList = jpaTemplate.getJdbcTemplate().queryForList(sql, repositoryId);

        return resultList;
    }

    public List<Map<String, Object>> getCaseTestResultNumberStatistics(CaseStatisticsModel caseStatisticsModel) {
        String repositoryId = caseStatisticsModel.getRepositoryId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(caseStatisticsModel.getStartTime());
        String endTime = sdf.format(caseStatisticsModel.getEndTime());

        String sql = "WITH FilteredInstances AS (\n" +
                "    SELECT\n" +
                "        type as case_type,\n" +
                "        status\n" +
                "    FROM\n" +
                "        teston_instance\n" +
                "    WHERE\n" +
                "        type != 'test-plan'  " ;

            String sql2 =  "  ),\n" +
                "TypeStatistics AS (\n" +
                "    SELECT\n" +
                "        case_type,\n" +
                "        SUM(CASE\n" +
                "            WHEN case_type = 'api-perform' AND status = 'complete' THEN 1\n" +
                "            WHEN case_type != 'api-perform' AND status = 'success' THEN 1\n" +
                "            ELSE 0\n" +
                "        END) AS pass,\n" +
                "        SUM(CASE\n" +
                "            WHEN case_type = 'api-perform' AND status NOT IN ('complete', 'start') THEN 1\n" +
                "            WHEN case_type != 'api-perform' AND status = 'fail' THEN 1\n" +
                "            ELSE 0\n" +
                "        END) AS fail,\n" +
                "        SUM(CASE\n" +
                "            WHEN status = 'error' THEN 1\n" +
                "            ELSE 0\n" +
                "        END) AS error\n" +
                "    FROM\n" +
                "        FilteredInstances\n" +
                "    GROUP BY\n" +
                "        case_type\n" +
                "),\n" +
                "TotalStatistics AS (\n" +
                "    SELECT\n" +
                "        'total' AS case_type,\n" +
                "        COALESCE(SUM(pass), 0) AS pass,\n" +
                "        COALESCE(SUM(fail), 0) AS fail,\n" +
                "        COALESCE(SUM(error), 0) AS error\n" +
                "    FROM\n" +
                "        TypeStatistics\n" +
                ")\n" +
                "SELECT * FROM TypeStatistics\n" +
                "UNION ALL\n" +
                "SELECT * FROM TotalStatistics;";


        if(repositoryId != null){
            String repositorySql= "  AND repository_id = '" +repositoryId+ "' " ;
            sql = sql+repositorySql;
        }

        String sesql = "  AND create_time BETWEEN '"+startTime+"'"+" AND " +"'"+endTime+"'";
        sql = sql+sesql;

        String allsql = sql+sql2;

        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(allsql);
        return maps;
    }


}
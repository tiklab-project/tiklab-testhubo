package net.tiklab.teston.repository.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.repository.model.RepositoryFollow;
import net.tiklab.teston.config.TestConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryFollowServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryFollowServiceImplTest.class);

    @Autowired
    RepositoryFollowService repositoryFollowService;

    static String id;

    @Test
    public void test01ForSaveRepositoryFollow() {
        RepositoryFollow repositoryFollow = JMockit.mock(RepositoryFollow.class);

        id = repositoryFollowService.createRepositoryFollow(repositoryFollow);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryFollow(){
        RepositoryFollow repositoryFollow = JMockit.mock(RepositoryFollow.class);
        repositoryFollow.setId(id);

        repositoryFollowService.updateRepositoryFollow(repositoryFollow);
    }

    @Test
    public void test03ForFindRepositoryFollow() {
        RepositoryFollow repositoryFollow = repositoryFollowService.findRepositoryFollow(id);

        assertNotNull(repositoryFollow);
    }

    @Test
    public void test04ForFindAllRepositoryFollow() {
        List<RepositoryFollow> repositoryFollowList = repositoryFollowService.findAllRepositoryFollow();

        assertNotNull(repositoryFollowList);
    }

    @Test
    public void test05ForDeleteRepositoryFollow(){
        repositoryFollowService.deleteRepositoryFollow(id);
    }
}
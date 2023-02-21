package net.tiklab.teston.repository.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.repository.model.RepositoryRecent;
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
public class RepositoryRecentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryRecentServiceImplTest.class);

    @Autowired
    RepositoryRecentService repositoryRecentService;

    static String id;

    @Test
    public void test01ForSaveRepositoryRecent() {
        RepositoryRecent repositoryRecent = JMockit.mock(RepositoryRecent.class);

        id = repositoryRecentService.createRepositoryRecent(repositoryRecent);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryRecent(){
        RepositoryRecent repositoryRecent = JMockit.mock(RepositoryRecent.class);
        repositoryRecent.setId(id);

        repositoryRecentService.updateRepositoryRecent(repositoryRecent);
    }

    @Test
    public void test03ForFindRepositoryRecent() {
        RepositoryRecent repositoryRecent = repositoryRecentService.findRepositoryRecent(id);

        assertNotNull(repositoryRecent);
    }

    @Test
    public void test04ForFindAllRepositoryRecent() {
        List<RepositoryRecent> repositoryRecentList = repositoryRecentService.findAllRepositoryRecent();

        assertNotNull(repositoryRecentList);
    }

    @Test
    public void test05ForDeleteRepositoryRecent(){
        repositoryRecentService.deleteRepositoryRecent(id);
    }
}
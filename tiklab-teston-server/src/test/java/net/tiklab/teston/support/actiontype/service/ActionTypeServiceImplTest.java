package net.tiklab.teston.support.actiontype.service;

import net.tiklab.teston.support.actiontype.model.ActionType;
import net.tiklab.postin.client.mock.JMockit;
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
public class ActionTypeServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ActionTypeServiceImplTest.class);

    @Autowired
    ActionTypeService actionTypeService;

    static String id;

    @Test
    public void test01ForSaveActionType() {
        ActionType actionType = JMockit.mock(ActionType.class);

        id = actionTypeService.createActionType(actionType);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateActionType(){
        ActionType actionType = JMockit.mock(ActionType.class);
        actionType.setId(id);

        actionTypeService.updateActionType(actionType);
    }

    @Test
    public void test03ForFindActionType() {
        ActionType actionType = actionTypeService.findActionType(id);

        assertNotNull(actionType);
    }

    @Test
    public void test04ForFindAllActionType() {
        List<ActionType> actionTypeList = actionTypeService.findAllActionType();

        assertNotNull(actionTypeList);
    }

    @Test
    public void test05ForDeleteActionType(){
        actionTypeService.deleteActionType(id);
    }
}
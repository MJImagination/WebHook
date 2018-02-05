package com.ancun.webhook;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebHookManageApplication.class)
@WebAppConfiguration
@TestPropertySource({"classpath:okhttpConfig.properties", "classpath:activeMQ.properties"})
public class BaseTest {

}

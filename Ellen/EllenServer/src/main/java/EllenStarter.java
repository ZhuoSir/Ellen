import com.chen.ellen.im.core.server.ImServer;
import com.chen.ellen.im.core.session.ImConnect;
import com.chen.ellen.process.DaemonTaskManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Created by sunny-chen on 2018/7/25.
 */
public class EllenStarter {

    public static void main(String[] args) {

        try {
            CountDownLatch latch = new CountDownLatch(1);
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

//            ImConnect imConnect = (ImConnect) ctx.getBean("imConnectImpl");
            ImServer imServer = (ImServer) ctx.getBean("imServer");
            imServer.init(6);
            imServer.bind(8888);
            imServer.start(false);

            // 启动后台服务守护线程
            DaemonTaskManager daemonTaskManager = (DaemonTaskManager) ctx.getBean("daemonTaskManager");
            daemonTaskManager.startTasks();

            ctx.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.AccountImClient;
import com.chen.ellen.im.core.ImClient;
import com.chen.ellen.im.core.ImClientMsgWrapper;
import com.chen.ellen.im.core.exception.ServerConnectionException;
import com.chen.ellen.im.core.handler.ChatReadListener;
import com.chen.ellen.im.shell.ChatService;
import com.chen.ellen.im.shell.ChatServiceImpl;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by sunny-chen on 2018/7/25.
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        ImAccount imAccount = new ImAccount();
        imAccount.setAccountId("bryan chen");
        imAccount.setNickName("Bryan");
        imAccount.setCreateTime(new Date());
        imAccount.setPassword("123456");

        ImClient imClient = new AccountImClient();
        try {
            imClient.setImAccount(imAccount);
            imClient.setChatReadListener(new ChatReadListener() {
                public void read(ImClientMsgWrapper wrapper) {
                    logger.info(wrapper.getTimeStamp() + ": " + wrapper.getContent());
                }
            });
            imClient.init(5);
            imClient.connect("127.0.0.1", 8888, true);

//            Session session = imClient.getSession();
//            session.send(c2SPacket);
//            System.out.println(session);

            ChatService chatService = new ChatServiceImpl(imClient);
//            chatService.setChatReadListener(new ChatReadListener() {
//                public void read(String msg) {
//                    System.out.println("2:" + msg);
//                }
//            });

//            CMessageWrapper.Builder msgBuilder = new CMessageWrapper.Builder();
//            msgBuilder.createDdate(new Date());
//            msgBuilder.receiver("");
//            msgBuilder.message("msg", "hello world");


        } catch (ServerConnectionException e) {
            e.printStackTrace();
            imClient.close();
        } finally {
//            imClient.close();
        }
    }
}

import com.chen.ellen.datao.ImAccount;
import com.chen.ellen.im.core.AccountImClient;
import com.chen.ellen.im.core.ImClient;
import com.chen.ellen.im.core.exception.ServerConnectionException;
import com.chen.ellen.im.shell.ChatService;
import com.chen.ellen.im.shell.ChatServiceImpl;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;

import java.util.Date;

/**
 * Created by sunny-chen on 2018/7/25.
 */
public class Main2 {

    public static void main(String[] args) {
        ImAccount imAccount = new ImAccount();
        imAccount.setAccountId("wang chen");
        imAccount.setNickName("Wang");
        imAccount.setCreateTime(new Date());
        imAccount.setPassword("123456");

        ImClient imClient = new AccountImClient();
        try {
            imClient.setImAccount(imAccount);
            imClient.init(5);
            imClient.connect("127.0.0.1", 8888, true);

//            Session session = imClient.getSession();
//            session.send(c2SPacket);
//            System.out.println(session);

            ChatServiceImpl chatService = new ChatServiceImpl();
            chatService.setImClient(imClient);

            CMessageWrapper.Builder msgBuilder = new CMessageWrapper.Builder();
            msgBuilder.createDdate(new Date());
            msgBuilder.receiver("4cdee942759d4135a83fa64fe9768542");
            msgBuilder.message("msg", "hello world");

            chatService.sendPersonalMessage(msgBuilder.build());

        } catch (ServerConnectionException e) {
            e.printStackTrace();
        } finally {
//            imClient.close();
        }
    }
}

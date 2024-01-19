package nong.soon.bae.contorller;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.spring.DefaultEmbeddableVerticle;

public class MessageChat extends DefaultEmbeddableVerticle {

    private SocketIOServer io = null;

    @Override
    public void start(Vertx vertx) {
        HttpServer server = vertx.createHttpServer();    // 서버 생성
        io = new DefaultSocketIOServer(vertx, server);

        io.sockets().onConnection(socket -> {
            socket.on("joinRoom", roomJoinEvent -> {
                String username = roomJoinEvent.getString("username");
                String sendname = roomJoinEvent.getString("sendname");
                String chatno = roomJoinEvent.getString("chatno");
                String roomIdentifier = getRoomIdentifier(username, sendname, chatno);
                socket.join(roomIdentifier);
                io.sockets().in(roomIdentifier).emit("join", roomJoinEvent);
            });
            
            socket.on("chatMsg", chatMsgEvent -> {
                String msg = chatMsgEvent.getString("msg");
                String sender = chatMsgEvent.getString("username");
                String receiver = chatMsgEvent.getString("sendname");
                String chatno = chatMsgEvent.getString("chatno");
                String roomIdentifier = getRoomIdentifier(sender, receiver, chatno);
                createChatFile(roomIdentifier, msg);
                io.sockets().in(roomIdentifier).emit("response", chatMsgEvent);
            });
            
            socket.on("outRoom", roomOutEvent -> {
            	String username = roomOutEvent.getString("username");
            	String sendname = roomOutEvent.getString("sendname");
            	String chatno = roomOutEvent.getString("chatno");
            	String roomIdentifier = getRoomIdentifier(username, sendname, chatno);
            	socket.leave(roomIdentifier);
            	io.sockets().in(roomIdentifier).emit("out", roomOutEvent);
            });

        });

        server.listen(9999);
    }

    private String getRoomIdentifier(String username, String sendname, String chatno) {
        String sortedNames = Stream.of(username, sendname)
                .sorted()
                .collect(Collectors.joining("_to_"));
        sortedNames = sortedNames + "_" + chatno;
        return sortedNames;
    }

    private void createChatFile(String roomIdentifier, String msg) {
        String filename = "D:\\dvsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\nongsoonbae\\resources\\chatRoom\\" + roomIdentifier + ".txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename, true);
            writer.write(msg);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
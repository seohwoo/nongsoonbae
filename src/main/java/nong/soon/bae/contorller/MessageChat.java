package nong.soon.bae.contorller;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

public class MessageChat extends DefaultEmbeddableVerticle{	
	private SocketIOServer io = null;
	
	@Autowired
	private PortMove portMove;
	private int num = 0;
	
	
	
	@Override
	public void start(Vertx arg0) {
		HttpServer server = arg0.createHttpServer();	//서버생성
		io = new DefaultSocketIOServer(arg0, server);
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			@Override
			public void handle(SocketIOSocket socket) {
				socket.on("chatMsg", new Handler<JsonObject>() {
					@Override
					public void handle(JsonObject event) {
						String msg = event.getString("msg"); // 받은 대화 내용
						String username = event.getString("username"); // 유저네임
						String sendname = event.getString("sendname"); // 상대이름
						num = Integer.parseInt(event.getString("num"));
						String fileRoot = "D:\\dvsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\nongsoonbae\\resources\\chatRoom\\";
						String filename = fileRoot+"\\"+username+"_to_"+sendname+".txt";
						Path path = Paths.get(filename);
						if(!Files.exists(path)) {
							filename = fileRoot+"\\"+sendname+"_to_"+username+".txt";
						}
						FileWriter writer = null;
						try {
							writer = new FileWriter(filename, true);
							writer.write(msg);
							writer.flush();
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 연결된 모든 사용자에게 대화 보내기
						io.sockets().emit("response", event); 
					}
				});
			}
		});
		server.listen(9999);
	}
}
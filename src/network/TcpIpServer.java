package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


 

public class TcpIpServer {
	HashMap clients; //아직 동기화가 안돼있다.
	
	TcpIpServer() {
		clients = new HashMap();		
		Collections.synchronizedMap(clients); //여럿이서 동시에 접속했을때 락을 걸어주는 동기화~ 한명이 들어가면 다른애가 기다려주는 역할.
	}

	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");

			while(true) {
				socket = serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속하였습니다.");
				ServerReceiver thread = new ServerReceiver(socket); //receiver 난 받겠다. 
				thread.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // start()

	void sendToAll(String msg) {
		Iterator it = clients.keySet().iterator();
		
		if(clients.containsKey("운전자")){
			DataOutputStream out = (DataOutputStream)clients.get(it.next());
			try {
				out.writeUTF(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*while(it.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			} catch(IOException e){}
		}*/ // while
	} // sendToAll

	
	
	public static void main(String args[]) {
		new TcpIpServer().start(); //그냥 함수 이름이 start(). object가 상송하는 start()함수가 아님.
	} 
	class ServerReceiver extends Thread {
		Socket socket;
		ObjectInputStream ois;
		ObjectOutputStream oos;

		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				ois  = new ObjectInputStream(socket.getInputStream()); 
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch(IOException e) {}
		}

		public void run() {
			Object id = null;
			try {
				id = ois.readObject();
				sendToAll("#"+id+"님이 들어오셨습니다.");

				clients.put(id, oos);
				System.out.println("현재 서버접속자 수는 "+ clients.size()+"입니다.");

				while(ois!=null) {
					sendToAll(ois.readUTF());
				}
			} catch(IOException e) {
				// ignore
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sendToAll("#"+id+"님이 나가셨습니다.");
				clients.remove(id);
				System.out.println("["+socket.getInetAddress() +":"+socket.getPort()+"]"+"에서 접속을 종료하였습니다.");
				System.out.println("현재 서버접속자 수는 "+ clients.size()+"입니다.");
			} // try
		} // run
	} // ReceiverThread
}

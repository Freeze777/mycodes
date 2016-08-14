package networks.sockets;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MegaClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean exitFlag=true;
		do {

			System.out.print("Make your choice:\n 1.Sort TCP Client\n 2.Sort UDP Client\n " +
					"3.Subnet Game TCP Client \n 4.Subnet Game UDP client\n Enter 0 to exit..!\n");
			Scanner sc=new Scanner(System.in);
			switch (sc.nextInt()) {
			case 0: exitFlag=false;
			sc.close();
			break;
			case 1:System.out.println("Enter numbers(comma seperated)");
			createTCPSortClient(sc.next());
			break;
			case 2:System.out.println("Enter numbers(comma seperated)");
			createUDPSortClient(sc.next());
			break;
			case 3:System.out.println("Enter 2 IPs and SubnetMask (format:IP1,1P2,SUBNET_MASK)");
			createTCPSubnetGameClient(sc.next());
			break;
			case 4:System.out.println("Enter 2 IPs and SubnetMask (format:IP1,1P2,SUBNET_MASK)");
			createUDPSubnetGameClient(sc.next());
			break;

			default:

				break;
			}
		} while (exitFlag);
	}

	private static void createUDPSubnetGameClient(String input) {
		// TODO Auto-generated method stub
		try {
			
			 
		      DatagramSocket clientSocket = new DatagramSocket();
		 
		      InetAddress serverIPAddress = InetAddress.getByName("172.168.131.178");
		      byte[] sendData = new byte[1024];
		      byte[] receiveData = new byte[1024];
		 
		     // String input = "192.168.1.100,192.168.2.102,255.255.224.0";
		    		  //inFromUser.readLine();
		 
		      sendData = input.getBytes();

		      DatagramPacket sendPacket =
		         new DatagramPacket(sendData, sendData.length, serverIPAddress, 65000);
		 
		      clientSocket.send(sendPacket);
		      
		 
		      DatagramPacket receivePacket =
		         new DatagramPacket(receiveData, receiveData.length);
		 
		      clientSocket.receive(receivePacket);
		 
		      String output =
		          new String(receivePacket.getData());
		      System.out.println("############FROM SERVER###########\n" + output+"\n");

		      clientSocket.close();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	private static void createTCPSubnetGameClient(String input) {
		// TODO Auto-generated method stub
		
		try {
		
			Socket clientSocket=new Socket("172.168.131.178",45000);//conncetion established
	System.out.println("TCP connection established");
	//	String input="192.168.61.100,192.168.2.102,255.255.224.0";
			
		DataOutputStream dataOut=new DataOutputStream(clientSocket.getOutputStream());
		dataOut.writeUTF(input);
		DataInputStream dataIn=new DataInputStream(clientSocket.getInputStream());
 System.out.println("############FROM SERVER###########\n" +dataIn.readUTF()+"\n");
		clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createUDPSortClient(String input) {
		// TODO Auto-generated method stub
		try {
			
			 
		      DatagramSocket clientSocket = new DatagramSocket();
		 
		      InetAddress serverIPAddress = InetAddress.getByName("172.168.131.178");
		      byte[] sendData = new byte[1024];
		      byte[] receiveData = new byte[1024];
		 
		     	 
		      sendData = input.getBytes();

		      DatagramPacket sendPacket =
		         new DatagramPacket(sendData, sendData.length, serverIPAddress, 35000);
		 
		      clientSocket.send(sendPacket);
		 
		      DatagramPacket receivePacket =
		         new DatagramPacket(receiveData, receiveData.length);
		 
		      clientSocket.receive(receivePacket);
		 
		      String output =
		          new String(new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength()));
		      System.out.println("############FROM SERVER###########\n" + output+"\n");

		      clientSocket.close();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private static void createTCPSortClient(String input) {
		// TODO Auto-generated method stub

		try {

			Socket clientSocket=new Socket("172.168.131.178",55000);//conncetion established
			System.out.println("TCP connection established");			
			DataOutputStream dataOut=new DataOutputStream(clientSocket.getOutputStream());
			dataOut.writeUTF(input);

			DataInputStream dataIn=new DataInputStream(clientSocket.getInputStream());
			System.out.println("############FROM SERVER###########\n" +dataIn.readUTF()+"\n");
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



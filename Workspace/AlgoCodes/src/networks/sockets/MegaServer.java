package networks.sockets;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

class BubbleSort {
	public static void bubbleSort(int[] array) {
		int c, d, swap;
		int n = array.length;
		for (c = 0; c < (n - 1); c++) {
			for (d = 0; d < n - c - 1; d++) {
				if (array[d] > array[d + 1]) /* For descending order use < */
				{
					swap = array[d];
					array[d] = array[d + 1];
					array[d + 1] = swap;
				}
			}
		}
	}
}

class Utility {

	public static synchronized String checkSubnetEquality(String inputIP) {
		String[] ip = inputIP.split("\\,");
		String[] temp;

		int[][] ips = new int[3][4];
		boolean flag = true;
		for (int i = 0; i < ip.length; i++) {
			temp = ip[i].split("\\.");
			for (int j = 0; j < temp.length; j++) {
				ips[i][j] = Integer.parseInt(temp[j].trim());
			}
		}

		for (int i = 0; i < 4; i++) {
			if ((ips[0][i] & ips[2][i]) != (ips[1][i] & ips[2][i])) {
				flag = false;
				break;
			}

		}
		if (flag)
			return "Yes,the IPs belong to same subnet";
		return "No,the IPs belong to different subnet";

	}

	public static synchronized String getSortedSequence(String inputNum) {
		String[] num = inputNum.split(",");
		int[] numbers = new int[num.length];

		for (int i = 0; i < num.length; i++)
			numbers[i] = Integer.parseInt(num[i].trim());

		BubbleSort.bubbleSort(numbers);

		inputNum = "";
		String temp = "";
		for (int i = 0; i < numbers.length; i++)
			temp = (i != numbers.length - 1) ? (inputNum += numbers[i] + ",")
					: (inputNum += numbers[i]);
		return inputNum;
	}
}

class SortHelper extends Thread {
	private Socket tcpSubSocket = null;
	private DatagramSocket udpSubSocket = null;
	private DatagramPacket receivePacket = null;

	public SortHelper(Socket subSocket) {
		// TODO Auto-generated constructor stub
		this.tcpSubSocket = subSocket;
	}

	public SortHelper(DatagramSocket udpSubSocket, DatagramPacket receivePacket) {
		this.receivePacket = receivePacket;
		this.udpSubSocket = udpSubSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if (tcpSubSocket != null) {
				DataInputStream dataIn = new DataInputStream(
						tcpSubSocket.getInputStream());
				String inputNum = dataIn.readUTF();
				System.out.println("Sort TCP..Input Recieved");
				String outNum = Utility.getSortedSequence(inputNum);
				DataOutputStream dataOut = new DataOutputStream(
						tcpSubSocket.getOutputStream());
				dataOut.writeUTF(outNum);
				tcpSubSocket.close();
			} else if (udpSubSocket != null && receivePacket != null) {
				String inputSequence = new String(receivePacket.getData(), 0,
						receivePacket.getLength());
				String reply = Utility.getSortedSequence(inputSequence);
				byte[] sendData = new byte[1024];
				sendData = reply.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, receivePacket.getAddress(),
						receivePacket.getPort());

				udpSubSocket.send(sendPacket);

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (udpSubSocket != null) {
				byte[] sendData = "corrupt data recieved".getBytes();
				try {
					udpSubSocket.send(new DatagramPacket(sendData,
							sendData.length, receivePacket.getAddress(),
							receivePacket.getPort()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class SubnetGameHelper extends Thread {
	private DatagramSocket udpGameSocket = null;
	private DatagramPacket receivePacket = null;
	private Socket tcpGameSocket = null;

	public SubnetGameHelper(Socket tcpGameSocket) {

		this.tcpGameSocket = tcpGameSocket;
	}

	public SubnetGameHelper(DatagramSocket udpGameSocket,
			DatagramPacket receivePacket) {

		this.udpGameSocket = udpGameSocket;
		this.receivePacket = receivePacket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if (udpGameSocket != null && receivePacket != null) {

				String inputIP = new String(receivePacket.getData());
				String reply = Utility.checkSubnetEquality(inputIP);
				byte[] sendData = new byte[1024];
				sendData = reply.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, receivePacket.getAddress(),
						receivePacket.getPort());

				udpGameSocket.send(sendPacket);

			} else if (tcpGameSocket != null) {
				DataInputStream dataIn = new DataInputStream(
						tcpGameSocket.getInputStream());
				String inputIP = dataIn.readUTF();
				System.out.println("Subnet Game TCP..Input Recieved");
				String reply = Utility.checkSubnetEquality(inputIP);
				DataOutputStream dataOut = new DataOutputStream(
						tcpGameSocket.getOutputStream());
				dataOut.writeUTF(reply);
				tcpGameSocket.close();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (udpGameSocket != null) {
				byte[] sendData = "corrupt data recieved".getBytes();
				try {
					udpGameSocket.send(new DatagramPacket(sendData,
							sendData.length, receivePacket.getAddress(),
							receivePacket.getPort()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SortRequestUDPListener extends Thread {
	private DatagramSocket subnetGameUDPServer;
	private byte[] receiveData = new byte[1024];

	public SortRequestUDPListener(DatagramSocket subnetGameUDPServer) {

		this.subnetGameUDPServer = subnetGameUDPServer;
	}

	@Override
	public void run() {

		try {

			while (true) {
				System.out.println("Sort UDP server running");
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);

				subnetGameUDPServer.receive(receivePacket);
				System.out.println("Sort UDP..Input Recieved");
				new SortHelper(subnetGameUDPServer, receivePacket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SortRequestTCPListener extends Thread {
	private ServerSocket welcomeSortSocket;

	public SortRequestTCPListener(ServerSocket welcomeSortSocket) {

		this.welcomeSortSocket = welcomeSortSocket;
	}

	@Override
	public void run() {
		try {

			while (true) {
				System.out.println("Sort TCP server running");
				new SortHelper(welcomeSortSocket.accept()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class SubnetGameTCPListener extends Thread {
	private ServerSocket welcomeSocket;

	public SubnetGameTCPListener(ServerSocket welcomeSocket) {
		this.welcomeSocket = welcomeSocket;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("Subnet Game TCP server running");
				new SubnetGameHelper(welcomeSocket.accept()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SubnetGameUDPListener extends Thread {
	private DatagramSocket subnetGameUDPServer;
	private byte[] receiveData = new byte[1024];

	public SubnetGameUDPListener(DatagramSocket subnetGameUDPServer) {

		this.subnetGameUDPServer = subnetGameUDPServer;
	}

	@Override
	public void run() {

		try {

			while (true) {
				System.out.println("Subnet Game UDP server running");
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);

				subnetGameUDPServer.receive(receivePacket);
				System.out.println("Subnet Game UDP..Input Recieved");
				new SubnetGameHelper(subnetGameUDPServer, receivePacket)
						.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class MegaServer {

	public static void main(String[] args) {
		try {
			// kill port: fuser -k -n tcp 45000 or
			// kill -9 $(lsof -i:45000 -t)

			ServerSocket tcpSubnetGameWelcomeSocket = new ServerSocket(45000);// subnet
																				// game
																				// tcp
																				// server
			ServerSocket tcpSortWelcomeSocket = new ServerSocket(55000);// sort
																		// tcp
																		// server
			DatagramSocket udpSubnetGameServerSocket = new DatagramSocket(65000);// subnet
																					// game
																					// udp
																					// server
			DatagramSocket udpSortServerSocket = new DatagramSocket(35000);// sort
																			// udp
																			// server
			new SortRequestTCPListener(tcpSortWelcomeSocket).start();
			new SortRequestUDPListener(udpSortServerSocket).start();
			new SubnetGameUDPListener(udpSubnetGameServerSocket).start();
			new SubnetGameTCPListener(tcpSubnetGameWelcomeSocket).start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}



public class Client {

	public static void main(String[] args) {
		HTLInternetAccess user1 = new Proxy("user1");
		user1.connect("youtube.com");
		user1.connect("tetris.com");
	}

}

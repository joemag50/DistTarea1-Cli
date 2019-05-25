import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket s = null;
		
		try
		{
			// instancio el server con la IP y el PORT
			s = new Socket("127.0.0.1",5400);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			Random rand = new Random();
			String mensaje = "";
			for (int i = 0; i < 10; i++) {
				int n = rand.nextInt(25);
				
				mensaje += n + ",";
			}
			
			oos.writeObject(mensaje);
			// recibo la respuesta (el saludo personalizado)
			String ret = (String)ois.readObject();
			// muestro la respuesta que envio el server
			System.out.println(ret);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if( ois != null ) ois.close();
			if( oos != null ) oos.close();
			if( s != null ) s.close();
		}
	}

}

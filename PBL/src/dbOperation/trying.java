package dbOperation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class trying {
	public static void main(String[] arg) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.now();
		String last_modify = format.format(ldt);
		System.out.println(last_modify);
	}
}

package org.richinfo.devops.passwdcheck.shadow;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenSmailPwd {

	public static void main(String[] args) throws IOException {
		List<String> lines = new ArrayList<String>();
		for (int i = 136; i <= 137; i++) {
			for (int j = 1; j < 255; j++) {
				lines.add("RichSmart2018!&%"+i+j);
			}
		}
		Path path = Paths.get("./pwd.txt");
		Files.write(path, lines, Charset.forName("UTF-8"));
	}

}

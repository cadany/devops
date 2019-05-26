package org.richinfo.devops.passwdcheck.shadow;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Formater {

	public static void main(String[] args) throws IOException {
		String feeds = "./feeds";
		if (args.length > 1 && args[1]!=null)
			feeds = args[1];
		String oFile = "./result.txt";
		
		Path feedPath = Paths.get(feeds);
		Formater formater = new Formater();
		List<String> lines = formater.start(feedPath);
		
		Path path = Paths.get(oFile);
		Files.write(path, lines, Charset.forName("UTF-8"));  
	}
	
	
	public List<String> start(Path feedPath) throws IOException {
		List<String> reduce = new ArrayList<String>();
		
		Files.walkFileTree(feedPath,  new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}
 
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问：" + file + "文件");
//				if (file.endsWith("Test.java")) {
//					System.out.println("******找到目标文件Test.java******");
//					return FileVisitResult.TERMINATE; // 找到了就终止
//				}
				if (file.getFileName().endsWith(".DS_Store")) {
					return FileVisitResult.CONTINUE;
				}
				formater(file, reduce);
				return FileVisitResult.CONTINUE; // 没找到继续找
			}
		});
		
		return reduce;
	}
	
	private void formater(Path file, List<String> reduce) throws IOException {
		List<String> lines = Files.readAllLines(file);
//		System.out.println(lines.size());
		for (String line : lines) {
			String[] items = line.split(":");
			if (items.length > 2)
				reduce.add(file.toString()+ "," + items[0] + "," + items[1]);
		}
	}

}

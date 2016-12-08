package com.tcj.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
	/**
	 * @param is
	 * @param file
	 * @return 灏嗕簩杩涘埗娴佽浆涓烘枃浠�
	 * @throws IOException
	 */
	public static void streamToFile(InputStream is, File file)
			throws IOException {
		OutputStream out = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[81920];
		while ((bytesRead = is.read(buffer, 0, 81920)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		is.close();
	}

}

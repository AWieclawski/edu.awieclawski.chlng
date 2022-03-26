package edu.file.get;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileResourceUtils {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private final static String RSRC_PTH = "resources";

	public static void main(String[] args) throws IOException {

		FileResourceUtils app = new FileResourceUtils();

		String fileName = String.format("%s/%s", RSRC_PTH, "json/test.json");

		System.out.println("\ngetResource : " + fileName);
		File file = app.getFileFromResource(fileName);
		app.printFile(file);

		System.out.println("\ngetResourceAsStream : " + fileName);
		InputStream is = app.getFileFromResourceAsStream(fileName);
		app.printInputStream(is);

		System.out.println("\ngetPathFromResourceUri : " + fileName);
		Path path = app.getPathFromResourceUri(fileName);
		app.printPath(path);
	}

	private File getFileFromResource(String fileName) {
		File result = null;
		try {
			result = handleFileFromResource(fileName);
		} catch (URISyntaxException e) {
			LOGGER.log(Level.SEVERE,
					String.format("URISyntaxException, file= %s, msg=%s", fileName, e.getMessage()));
		}
		return result;
	}

	private InputStream getFileFromResourceAsStream(String fileName) {
		InputStream result = handleFileFromResourceAsStream(fileName);

		return result;
	}

	private Path getPathFromResourceUri(String fileName) {
		Path result = null;
		try {
			result = handlePathFromResourceUri(fileName);
		} catch (URISyntaxException e) {
			LOGGER.log(Level.SEVERE,
					String.format("URISyntaxException, file= %s, msg=%s", fileName, e.getMessage()));
		}
		return result;
	}

	/*
	 * The resource URL is not working in the JAR If we try to access a file that is
	 * inside a JAR, It throws NoSuchFileException (linux), InvalidPathException
	 * (Windows)
	 * 
	 * Resource URL Sample: file:java-io.jar!/json/file1.json
	 */
	private File handleFileFromResource(String fileName) throws URISyntaxException {

		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		}
		else {

			// failed if files have whitespaces or special characters
			// return new File(resource.getFile());

			return new File(resource.toURI());
		}

	}

	private Path handlePathFromResourceUri(String fileName) throws URISyntaxException {

		URI resource = getClass().getClassLoader().getResource(fileName).toURI();
		if (resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		}
		else {
			return Paths.get(resource); // new Path(resource.toURI());
		}

	}

	// get a file from the resources folder
	// works everywhere, IDEA, unit test and JAR file.
	private InputStream handleFileFromResourceAsStream(String fileName) {

		// The class loader that loaded the class
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		}
		else {
			return inputStream;
		}

	}

// print a file
	private void printFile(File file) {

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			lines.forEach(System.out::println);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
					String.format("IOException , file=%s, msg=%s", file, e.getMessage()));

		}

	}

// print a file
	private void printPath(Path path) {

		List<String> lines;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.forEach(System.out::println);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
					String.format("IOException , file=%s, msg=%s", path, e.getMessage()));

		}

	}

	// print input stream
	private void printInputStream(InputStream is) {

		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
					String.format("IOException , is=%s, msg=%s", is, e.getMessage()));
		}

	}

}

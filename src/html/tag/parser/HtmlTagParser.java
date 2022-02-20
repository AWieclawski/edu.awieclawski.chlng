package html.tag.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * As input we get an HTML file. Write a program that will delete all of them
 * HTML tags and will keep only the text they contain. Result should be
 * displayed on the screen. *
 * 
 * @author AWieclawski
 *
 */

public class HtmlTagParser {

	private final static Logger LOGGER = Logger.getLogger(HtmlTagParser.class.getName());

	public static void main(String[] args) {

		run("src/resources/html.txt");

	}

	private static void run(String filePath) {
		Collection<String> linesList = new ArrayList<>();

		try {
			linesList = getLinesFromFile(filePath);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, String.format("Can not get lines from file: %s", filePath));
		}

		print(replaceMultiNewLines(removeTags(readLinesToString(linesList))));
	}

	private static String replaceMultiNewLines(String input) {

		if (isStringNullOrZeroLength(input)) {
			return input;
		}

		input = input.replaceAll("\n", "#");

		while (input.contains("##")) {
			input = input.replaceAll("##", "#");
		}
		input = input.replaceAll("#", "\n");

		return input;
	}

	private static String removeTags(String input) {

		if (isStringNullOrZeroLength(input)) {
			return input;
		}

		final Pattern[] patternArray = { Pattern.compile("<.+?>"), Pattern.compile("<!---"), Pattern.compile("-->"), };

		for (Pattern pattern : patternArray) {
			Matcher m = pattern.matcher(input);
			input = m.replaceAll("");
		}

		return input;
	}

	private static void print(String txt) {
		System.out.println(txt);
	}

	private static Collection<String> getLinesFromFile(String pathFile) throws IOException {
		Path path = Paths.get(pathFile);

		if (Files.exists(path)) {
			return Files.readAllLines(path);
		}

		LOGGER.log(Level.SEVERE, String.format("File not found: %s", pathFile));
		throw new FileNotFoundException();
	}

	private static String readLinesToString(Collection<String> lines) {
		StringBuilder resultStringBuilder = new StringBuilder();

		for (String line : lines) {
			resultStringBuilder.append(line).append("\n");
		}

		return resultStringBuilder.toString();
	}

	private static boolean isStringNullOrZeroLength(String input) {

		return (input == null || input.length() == 0);
	}

}

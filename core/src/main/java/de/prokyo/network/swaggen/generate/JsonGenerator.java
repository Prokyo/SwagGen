package de.prokyo.network.swaggen.generate;

import de.prokyo.network.swaggen.core.Node;
import de.prokyo.network.swaggen.core.Value;
import java.util.Map;

/**
 * The JsonGenerator generates swagger suitable json output.
 */
public class JsonGenerator {

	/**
	 * Generates a swagger suitable json output and returns it.
	 *
	 * @param root The root node of the k-dimensional tree
	 * @return The swagger suitable json output
	 * @see JsonGenerator#generateSwaggerJson(String, Node, StringBuilder, int)
	 */
	public static String generateSwaggerJson(Node root) {
		StringBuilder builder = new StringBuilder();
		generateSwaggerJson("", root, builder, 0);
		return builder.toString();
	}

	/**
	 * Generates a swagger suitable json output and write it to the given builder.
	 *
	 * @param key The key or name of a json block (in this case the node)
	 * @param node The root node (json block)
	 * @param builder The StringBuilder in which the output will be written to
	 * @param dist The distance to the left edge in spaces. This parameter is just for pretty printing.
	 */
	private static void generateSwaggerJson(String key, Node node, StringBuilder builder, int dist) {
		builder.append(genSpaces(dist));
		if (!key.isEmpty()) {
			builder.append('"');
			builder.append(key);
			builder.append("\": ");
		}

		addCurly(dist, builder, false);


		int i = 1;
		for(Map.Entry<String, Value> entry : node.getProperties().entrySet()) {
			if(entry.getValue() instanceof Node) {
				generateSwaggerJson(entry.getKey(), (Node) entry.getValue(), builder, dist + 2);
			} else {
				addKeyValue(dist + 2, builder, entry.getKey(), entry.getValue(), i < node.getProperties().size());
			}
			i++;
		}

		addCurly(dist, builder, true);
	}

	/**
	 * Generates the output for the given key and value and writes it to the given <i>builder</i>.
	 *
	 * @param dist The distance to the left edge in spaces; This parameter is just for pretty printing.
	 * @param builder The StringBuilder in which the output will be written to
	 * @param key The key of the key-value-pair
	 * @param value The corresponding value of the key-value-pair
	 * @param appendComma Whether a comma shall be appended at the end or not
	 */
	private static void addKeyValue(int dist, StringBuilder builder,  String key, Value value, boolean appendComma) {
		builder.append(genSpaces(dist));
		builder.append('"');
		builder.append(key);
		builder.append("\": ");
		builder.append(value.toString());
		if(appendComma) builder.append(',');
		builder.append('\n');
	}

	/**
	 * Adds a culy to the output.
	 *
	 * @param dist The distance to the left edge in spaces; This parameter is just for pretty printing.
	 * @param builder The StringBuilder in which the output will be written to
	 * @param closing Whether the curly is a closing or opening curly
	 */
	private static void addCurly(int dist, StringBuilder builder, boolean closing) {
		builder.append(genSpaces(dist));
		builder.append(closing ? '}' : '{');
		//TODO: enabling/disabling pretty-printing
		builder.append('\n');
	}

	/**
	 * Generates the given <i>amount</i> of spaces and returns the result.
	 *
	 * @param amount The amount of spaces
	 * @return A String containing the given amount of spaces
	 */
	private static String genSpaces(int amount) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 0; i < amount; i++) spaces.append(" ");
		return spaces.toString();
	}

}

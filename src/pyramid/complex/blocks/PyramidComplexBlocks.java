package pyramid.complex.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The pyramid has a square base (n x n) and is built of stone blocks. Blocks
 * are stacked in the same order from left to right (rows) and bottom to top
 * (columns). Each subsequent level follows the same principle. Each subsequent
 * level has n-1 blocks in each row and column.
 * 
 * @author AWieclawski
 *
 */
public class PyramidComplexBlocks {

	public static int value;

	public static void main(String[] args) {

//		print(pyramidBuilder(3).toString());

		countElements(3);

		getBlockNumber(4, 1, 2, 2);

	}

	public static int getBlockNumber(int side, int row, int col, int level) {
		BlockData blockData = pyramidBuilder(side).stream()
				.filter(block -> block.getLevel() == level
						&& block.getRow() == row
						&& block.getCol() == col)
				.findAny()
				.orElseThrow(() -> new NoSuchElementException("Block number not found"));
		int result = blockData.getValue();
		print(String.format(" BlockNumber=%d, where sides size=%d, level=%d, row=%d, col=%d",
				result, side, level, row,
				col));

		return result;
	}

	public static int countElements(int side) {
		int result = pyramidBuilder(side).stream()
				.mapToInt(v -> v.getValue()).max()
				.orElseThrow(() -> new NoSuchElementException("Elements number not calculated"));
		print(String.format(" CountElements=%d, where sides size=%d", result, side));

		return result;
	}

	private static List<BlockData> pyramidBuilder(int side) {
		int levelSide = side;
		int level = 1;
		value = 1;
		List<BlockData> blockList = new ArrayList<>();

		while (levelSide > 0) {
			blockList.addAll(fillLevel(level, levelSide));
			level++;
			levelSide--;
		}

		return blockList;
	}

	private static List<BlockData> fillLevel(int level, int levelSide) {
		List<BlockData> levelBlockList = new ArrayList<>();

		for (int row = 1; row <= levelSide; row++) {
			for (int col = 1; col <= levelSide; col++) {
				BlockData blockData = new BlockData(level, row, col, value);
				levelBlockList.add(blockData);
				value++;
			}
		}

		return levelBlockList;
	}

	private static void print(String txt) {
		System.out.println(txt);
	}

	public static class BlockData {
		private int level;
		private int row;
		private int col;
		private int value;

		public BlockData(int level, int row, int col, int value) {
			this.level = level;
			this.row = row;
			this.col = col;
			this.value = value;
		}

		public int getLevel() {
			return level;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "BlockData [level=" + level + ", row=" + row + ", col=" + col + ", value="
					+ value + "] \n";
		}

	}

}

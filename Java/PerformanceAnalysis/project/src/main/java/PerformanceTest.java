
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;
import view.View;

public class PerformanceTest {
	private static final int[] ARRAY = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static void main(String[] args) {

		View view = new View();
		while (!view.START) {
			System.out.print("");
		}

		try {
			FileWriter fileWriter = new FileWriter("Results.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.write("Results\n");
			view.write("Results\n");

			findHardware(printWriter, view);
			runTransferSpeedTests(printWriter, view);
			runAssemblyOperations(printWriter, view);

			printWriter.close();
		} catch (IOException e) {
			System.out.println("Cannot create file");
		}

	}

	private static void findHardware(PrintWriter printWriter, View view) {
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		printWriter.write("Processor: " + hal.getProcessor().getName() + "\n");
		printWriter.write("Frequency: " + hal.getProcessor().getVendorFreq() + "\n");
		printWriter.write("Memory: " + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/"
				+ FormatUtil.formatBytes(hal.getMemory().getTotal()) + "\n");
		view.write("Processor: " + hal.getProcessor().getName() + "\n");
		view.write("Frequency: " + hal.getProcessor().getVendorFreq() + "\n");
		view.write("Memory: " + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/"
				+ FormatUtil.formatBytes(hal.getMemory().getTotal()) + "\n");
	}

	private static long runTransferTest() {

		long startTime = 0, endTime = 0;
		File f1 = new File("Transfer.dat");
		File f2 = new File("Transfered.dat");
		try {

			RandomAccessFile transferFile = new RandomAccessFile(f1, "rw");
			transferFile.setLength(1024 * 1024 * 1);
			RandomAccessFile transferedFile = new RandomAccessFile(f2, "rw");
			byte[] data = new byte[1024 * 1024 * 1];
			startTime = System.nanoTime();

			transferFile.read(data);
			transferedFile.write(data);

			endTime = System.nanoTime();

			transferFile.close();
			transferedFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		f1.deleteOnExit();
		f2.deleteOnExit();
		return endTime - startTime;
	}

	private static void runTransferSpeedTests(PrintWriter printWriter, View view) {
		long totalTime = 0;
		long time;
		double timeDouble;
		for (int i = 0; i < 5; i++) {
			time = runTransferTest();
			timeDouble = time / 1000000.0;
			printWriter.write("Timing of test " + (i + 1) + " for data transfer (1 MB): " + time + " nanoseconds ~ "
					+ timeDouble + " milliseconds\n");
			view.write("Timing of test " + (i + 1) + " for data transfer (1 MB): " + time + " nanoseconds ~ "
					+ timeDouble + " milliseconds\n");
			totalTime += time;
		}
		timeDouble = totalTime / 1000000.0 / 5;
		totalTime /= 5;
		printWriter.write(
				"Average time of transfer tests: " + totalTime + " nanoseconds ~ " + timeDouble + " milliseconds\n");
		view.write("Average time of transfer tests: " + totalTime + " nanoseconds ~ " + timeDouble + " milliseconds\n");
	}

	private static void runAssemblyOperations(PrintWriter printWriter, View view) {
		try {
			final Process process = Runtime.getRuntime().exec("cmd /c build.bat");
			process.waitFor();
		} catch (IOException e) {
			printWriter.write("Build not run\n");
			e.printStackTrace();
		} catch (InterruptedException e) {
			printWriter.write("Build interrupted");
			e.printStackTrace();
		}

		long startTime, endTime, time;
		double timeDouble;
		File file = new File("libPerformanceTest.so");
		System.load(file.getAbsolutePath());

		// Sum
		startTime = System.nanoTime();
		long sum = computeArraySum(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter.write("Timing of addition test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the sum is: " + sum + "\n");
		view.write("Timing of addition test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the sum is: " + sum + "\n");

		// Substraction
		startTime = System.nanoTime();
		long sub = computeArraySub(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter.write("Timing of substraction test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the substraction is: " + sub + "\n");
		view.write("Timing of substraction test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the substraction is: " + sub + "\n");

		// Multiplication
		startTime = System.nanoTime();
		long mul = computeArrayMul(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter
				.write("Timing of multiplication test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the multiplication is: " + mul + "\n");
		view.write("Timing of multiplication test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the multiplication is: " + mul + "\n");

		// AND
		startTime = System.nanoTime();
		long andOp = computeArrayAnd(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter.write("Timing of AND test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the AND is: " + andOp + "\n");
		view.write("Timing of AND test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the AND is: " + andOp + "\n");

		// OR
		startTime = System.nanoTime();
		long orOp = computeArrayOr(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter.write("Timing of OR test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the OR is: " + orOp + "\n");
		view.write("Timing of OR test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the OR is: " + orOp + "\n");

		// XOR
		startTime = System.nanoTime();
		long xorOp = computeArrayXor(ARRAY, ARRAY.length);
		endTime = System.nanoTime();
		time = endTime - startTime;
		timeDouble = time / 1000000.0;
		printWriter.write("Timing of XOR test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		printWriter.write("The result of the XOR is: " + xorOp + "\n");
		view.write("Timing of XOR test: " + time + " nanoseconds ~ " + timeDouble + " milliseconds\n");

		view.write("The result of the XOR is: " + xorOp + "\n");
	}

	public static native long computeArraySum(int[] array, int arrayLength);

	public static native long computeArraySub(int[] array, int arrayLength);

	public static native long computeArrayMul(int[] array, int arrayLength);

	public static native long computeArrayAnd(int[] array, int arrayLength);

	public static native long computeArrayOr(int[] array, int arrayLength);

	public static native long computeArrayXor(int[] array, int arrayLength);

}

package parser;

import support.Regs;

/**
 * Simple Virtual Machine.
 */
public class ExecuteVM {

	public static final int CODESIZE = 10_000;

	public static final int MEMSIZE = 40_000;

	private int[] code;

	/*
	 * Split in two logical parts Heap: starts from 0 and goes forward Stack: starts
	 * from the last element and goes backwards
	 */
	private byte[] memory = new byte[MEMSIZE];

	/** List of registers. */
	private int[] regs = new int[Regs.values().length];

	/** Instruction pointer. */
	private int ip = 0;

	public ExecuteVM(int[] code) {
		this.code = code;

		regs[Regs.$sp.ordinal()] = MEMSIZE;
		regs[Regs.$fp.ordinal()] = MEMSIZE;
	}

	/**
	 * Execute the SVM code passed in constructor.
	 */
	public void cpu() {
		// Variable for general purpose.
		// Declared here to speedup the Java's garbage collection process
		int v1;

		while (true) {
			if (regs[Regs.$hp.ordinal()] + 1 >= regs[Regs.$sp.ordinal()]) {
				System.err.println("\nError: Out of memory");
				return;
			}
			int bytecode = code[ip++];

			switch (bytecode) {
			case 0:
				return;
			// LOADINTEGER REG NUMBER
			case SVMParser.LOADINTEGER:
				regs[code[ip++]] = code[ip++];
				break;
			// BRANCH address_to_jump_into
			case SVMParser.BRANCH:
				// the ip points to the starting code address of the label to jump into
				ip = code[ip];
				break;
			// PUSH REG NUMBER
			case SVMParser.PUSH:
				// Decrease $sp value of the dimension of the variable to store (1 for
				// bool, 4 for int)
				regs[Regs.$sp.ordinal()] = regs[Regs.$sp.ordinal()] - code[ip + 1];

				/**
				 * The integer to save is split in bytes.<br>
				 * The most significant byte is stored in the lowest stack position, the others
				 * are saved in order.
				 * 
				 * -----------------AKA-----------------
				 * 
				 * For each byte value of the integer number, save it into the stack.<br>
				 * Add the most significant byte at address position and go up saving the others
				 * in decreasing order.
				 * 
				 * Example (with $sp = 3000): memory[3000] = most significant byte<br>
				 * memory[3001] = ... memory[3002] = ... memory[3003] = least significant byte
				 */

				// code[ip + 1] contains the dimension of the value to store in the stack
				for (v1 = 0; v1 < code[ip + 1]; v1++)
					// decToByte is kept for sake of readability but, for sake of optimization, it
					// should be replace by the instruction into the method itself.
					memory[regs[Regs.$sp.ordinal()] + v1] = decToByte(regs[code[ip]], (byte) (code[ip + 1] - v1 - 1));
				// skip REG and NUMBER elements
				ip += 2;
				break;
			// LOADWORD r1=REG offset=NUMBER '(' r2=REG ')' dimension=NUMBER
			case SVMParser.LOADWORD:
				// code[ip + 3] represents the variable dimension
				if (code[ip + 3] == 1) // the value to load is boolean (1 byte)
					// The three most significant bytes are 0
					/**
					 * <code>memory[code[ip + 1] + regs[code[ip + 2]]</code> retrieves the value
					 * from the stack (code[ip+2]) summed to the offset (code[ip+1])
					 */
					regs[code[ip]] = byteToDec((byte) 0, (byte) 0, (byte) 0, memory[code[ip + 1] + regs[code[ip + 2]]]);
				else // The value is an integer
						// Retrieve all the byte values in the 4 positions after the initial variable
						// address (reg_value + offset applied to the stack)
						// byteToDec is kept for sake of readability but, for sake of optimization, it
						// should be replace by the instruction into the method itself.
					regs[code[ip]] = byteToDec(memory[code[ip + 1] + regs[code[ip + 2]]],
							memory[code[ip + 1] + regs[code[ip + 2]] + 1],
							memory[code[ip + 1] + regs[code[ip + 2]] + 2],
							memory[code[ip + 1] + regs[code[ip + 2]] + 3]);
				ip += 4;
				break;
			// STOREWORD r1=REG offset=NUMBER '(' r2=REG ')' dimension=NUMBER
			case SVMParser.STOREWORD:
				for (v1 = 0; v1 < code[ip + 3]; v1++)
					// Store bytes of the value in regs[code[ip]] starting from the address given by
					// regs[code[ip + 2]] summed to the offset in code[ip + 1]
					memory[code[ip + 1] + regs[code[ip + 2]] + v1] = decToByte(regs[code[ip]],
							(byte) (code[ip + 3] - v1 - 1));
				ip += 4;
				break;
			case SVMParser.ADD:
				regs[code[ip++]] = regs[code[ip++]] + regs[code[ip++]];
				break;
			case SVMParser.SUB:
				regs[code[ip++]] = regs[code[ip++]] - regs[code[ip++]];
				break;
			case SVMParser.MUL:
				regs[code[ip++]] = regs[code[ip++]] * regs[code[ip++]];
				break;
			case SVMParser.DIV:
				regs[code[ip++]] = regs[code[ip++]] / regs[code[ip++]];
				break;
			// POP NUMBER
			case SVMParser.POP:
				/** <code>code[ip++]</code> represents the variable dimension */
				regs[Regs.$sp.ordinal()] = regs[Regs.$sp.ordinal()] + code[ip++];
				break;
			// BRANCHEQ r1=REG r2=REG address_to_jump_into
			case SVMParser.BRANCHEQ:
				// for sake of optimization, we choose to avoid another function call copying
				// and pasting the almost same code for each if-commands
				// ifBranch((a, b) -> a == b);
				if (regs[code[ip++]] == regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			// BRANCHGT r1=REG r2=REG address_to_jump_into
			case SVMParser.BRANCHGT:
				// for sake of optimization, we choose to avoid another function call copying
				// and pasting the almost same code for each if-commands
				// ifBranch((a, b) -> a > b);
				if (regs[code[ip++]] > regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			// BRANCHGE r1=REG r2=REG address_to_jump_into
			case SVMParser.BRANCHGE:
				// for sake of optimization, we choose to avoid another function call copying
				// and pasting the almost same code for each if-commands
				// ifBranch((a, b) -> a >= b);
				if (regs[code[ip++]] >= regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			// BRANCHLT r1=REG r2=REG address_to_jump_into
			case SVMParser.BRANCHLT:
				// for sake of optimization, we choose to avoid another function call copying
				// and pasting the almost same code for each if-commands
				// ifBranch((a, b) -> a < b);
				if (regs[code[ip++]] < regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			// BRANCHLE r1=REG r2=REG address_to_jump_into
			case SVMParser.BRANCHLE:
				// for sake of optimization, we choose to avoid another function call copying
				// and pasting the almost same code for each if-commands
				// ifBranch((a, b) -> a <= b);
				if (regs[code[ip++]] <= regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			// NEG REG
			case SVMParser.NEG:
				regs[code[ip]] = -regs[code[ip++]];
				break;
			// MOVE dest=REG origin=REG
			case SVMParser.MOVE:
				regs[code[ip++]] = regs[code[ip++]];
				break;
			// ADDINTEGER dest=REG r1=REG NUMBER
			case SVMParser.ADDINTEGER:
				regs[code[ip++]] = regs[code[ip++]] + code[ip++];
				break;
			// JUMPLABEL address_to_jump_into
			case SVMParser.JUMPLABEL:
				// Store return address (next ip)
				regs[Regs.$ra.ordinal()] = ip + 1;
				// Jump to the required label
				ip = code[ip];
				break;
			// JUMPREG
			case SVMParser.JUMPREG:
				// Jump to the address in $ra register
				ip = regs[Regs.$ra.ordinal()];
				break;
			// PRINT
			case SVMParser.PRINT:
				System.out.println(regs[Regs.$a0.ordinal()]);
				break;
			default:
				System.err.println("Unsupported bytecode: " + bytecode);
				return;
			}
		}
	}

	/**
	 * Calculates a specific byte value of an integer number.
	 * 
	 * @param number the integer number
	 * @param i      the position of the required byte
	 * @return the byte
	 */
	private static byte decToByte(int number, byte i) {
		/*
		 * The size of integer numbers is 4 bytes.
		 * 
		 * To obtain the value of a specific byte of the number, it shifts right the
		 * value by 8 bits multiplied with the position of the required byte. So, for
		 * instance, if index equals to 0 we have the least significant byte.
		 * 
		 * In this way the required byte will be the last position byte of the new
		 * integer number. Then, it's possible to return the last byte value casting the
		 * number to byte.
		 */
		return (byte) (number >> 8 * i);
	}

	/**
	 * Take a sequence of byte values and calculates the correspondent integer
	 * value. <br>
	 * The sequence must be passed from the most significant byte to the least.
	 * 
	 * @param numbers the byte values that build the number
	 * @return the integer built value
	 */
	private static int byteToDec(byte b1, byte b2, byte b3, byte b4) {
		// "& 0xFF" turns signed number to binary value, so we can shift this without be
		// aware of the sign.
		return ((b1 & 0xFF) << 24) | ((b2 & 0xFF) << 16) | ((b3 & 0xFF) << 8) | ((b4 & 0xFF) << 0);

	}

//	private void ifBranch(BiFunction<Integer, Integer, Boolean> condition) {
//	if (condition.apply(regs[code[ip++]], regs[code[ip++]]))
//		ip = code[ip];
//	else
//		ip++;
//}

}
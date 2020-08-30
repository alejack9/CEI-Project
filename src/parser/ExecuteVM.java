package parser;

import support.Regs;

/**
 * SVM Virtual Machine.
 */
public class ExecuteVM {

	public static final int CODESIZE = 10_000;
	
	public static final int MEMSIZE = 40_000;

	private int[] code;
	
	/*
	 * Heap: starts from 0 and goes forward
	 * Stack: starts from the last element and goes backwards
	 */
	private byte[] memory = new byte[MEMSIZE];

	/** List of registers. */
	private int[] regs = new int[Regs.values().length];

	/** Instruction pointer. */
	private int ip = 0;

	public ExecuteVM(int[] code) {
		this.code = code;
		
		setRegValue(Regs.$sp, MEMSIZE);
		setRegValue(Regs.$fp, MEMSIZE);
	}

	/**
	 * Executes the SVM code.
	 */
	public void cpu() {
		while (true) {
			if (getRegValue(Regs.$hp) + 1 >= getRegValue(Regs.$sp)) {
				System.err.println("\nError: Out of memory");
				return;
			}
			int bytecode = code[ip++];
			int v1;

			switch (bytecode) {
			case 0:
				return;
			case SVMParser.LOADINTEGER:
				regs[code[ip++]] = code[ip++];
				break;
			case SVMParser.BRANCH:
				ip = code[ip];
				break;
			case SVMParser.PUSH:
				setRegValue(Regs.$sp, getRegValue(Regs.$sp) - code[ip + 1]);
				/*
				 * Requests every byte value of the integer number in order to save it into the stack. 
				 * It adds the most significant byte at address position and go up saving the others in decreasing order.
				 * 
				 * example (with address = 3000):
				 * 	memory[3000] = most significant byte
				 * 	memory[3001] = ...
				 * 	memory[3002] = ...
				 * 	memory[3003] = least significant byte
				 */
				
				//code[ip + 1] contains the dimension of the value to save in the stack (1 for bool, 4 for int)		
				for (v1 = 0; v1 < code[ip + 1]; v1++)
					memory[getRegValue(Regs.$sp) + v1] = decToByte(regs[code[ip]], (byte) (code[ip + 1] - v1 - 1));
				ip += 2;
				break;
			case SVMParser.LOADWORD:
				if (code[ip + 3] == 1) // the value to load is boolean (1 byte)
					regs[code[ip]] = byteToDec((byte) 0, (byte) 0, (byte) 0, memory[code[ip + 1] + regs[code[ip + 2]]]);
				else // the value is an integer so it retrieves all the byte values in the 4 positions after the address
					regs[code[ip]] = byteToDec(memory[code[ip + 1] + regs[code[ip + 2]]],
							memory[code[ip + 1] + regs[code[ip + 2]] + 1],
							memory[code[ip + 1] + regs[code[ip + 2]] + 2],
							memory[code[ip + 1] + regs[code[ip + 2]] + 3]);
				ip += 4;
				break;
			case SVMParser.STOREWORD:
				for (v1 = 0; v1 < code[ip + 3]; v1++)
					// stores bytes of the value in code[ip] starting from the address given by code[ip + 2] + the offset in code[ip + 1]
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
			case SVMParser.POP:
				setRegValue(Regs.$sp, getRegValue(Regs.$sp) + code[ip++]);
				break;
			case SVMParser.BRANCHEQ:
				if (regs[code[ip++]] == regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;
			case SVMParser.BRANCHGT:
				if (regs[code[ip++]] > regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;

			case SVMParser.BRANCHGE:
				if (regs[code[ip++]] >= regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;

			case SVMParser.BRANCHLT:
				if (regs[code[ip++]] < regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;

			case SVMParser.BRANCHLE:
				if (regs[code[ip++]] <= regs[code[ip++]])
					ip = code[ip];
				else
					ip++;
				break;

			case SVMParser.NEG:
				regs[code[ip]] = -regs[code[ip++]];
				break;

			case SVMParser.MOVE:
				regs[code[ip++]] = regs[code[ip++]];
				break;

			case SVMParser.ADDINTEGER:
				regs[code[ip++]] = regs[code[ip++]] + code[ip++];
				break;

			case SVMParser.JUMPLABEL:
				setRegValue(Regs.$ra, ip + 1);
				ip = code[ip];
				break;

			case SVMParser.JUMPREG:
				ip = getRegValue(Regs.$ra);
				break;

			case SVMParser.PRINT:
				System.out.println(getRegValue(Regs.$a0));
				break;

			default:
				System.err.println("Unsupported bytecode: " + bytecode);
				return;
			}
		}
	}

	/**
	 * Gets the register value.
	 *
	 * @param reg the enum reg name
	 * @return the reg value
	 */
	private int getRegValue(Regs reg) {
		return regs[reg.ordinal()];
	}

	/**
	 * Sets the register value.
	 *
	 * @param reg the enum reg name
	 * @param value the value
	 */
	private void setRegValue(Regs reg, int value) {
		regs[reg.ordinal()] = value;
	}

	/**
	 * Calculates a specific byte value of an integer number.
	 * 
	 * @param number the integer number
	 * @param i the position of the required byte
	 * @return the byte
	 */
	private static byte decToByte(int number, byte i) {
		/*
		 * The size of integer numbers is 4 bytes.
		 * 
		 * To obtain the value of a specific byte of the number, it shifts right the value by 8 bits multiplied with the position of the required byte.
		 * So, for instance, if index equals to 0 we have the least significant byte.
		 * 
		 * In this way the required byte will be the last position byte of the new integer number.
		 * Then, it's possible to return the last byte value casting the number to byte.
		 */
		return (byte) (number >> 8 * i);
	}

	/**
	 * Takes a sequence of byte values and calculates the correspondent integer value.
	 *<br>
	 * The sequence must be passed from the byte more significant byte to lowest.
	 * @param numbers the byte values
	 * @return the integer
	 */
	private static int byteToDec(byte... numbers) {
		// "& 0xFF" turns signed number to binary value, so we can shift this without be  aware of the sign.
		return ((numbers[0] & 0xFF) << 24) | ((numbers[1] & 0xFF) << 16) | ((numbers[2] & 0xFF) << 8)
				| ((numbers[3] & 0xFF) << 0);

	}
}
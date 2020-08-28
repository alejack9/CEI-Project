package parser;

import support.Regs;

public class ExecuteVM {

	public static final int CODESIZE = 10_000;
	public static final int MEMSIZE = 40_000;

	private int[] code;
	private byte[] memory = new byte[MEMSIZE];

	private int[] regs = new int[Regs.values().length];

	private int ip = 0;

	public ExecuteVM(int[] code) {
		this.code = code;

		setRegValue(Regs.$sp, MEMSIZE);
		setRegValue(Regs.$fp, MEMSIZE - 4);
	}

	public void cpu() {
		while (true) {
			if (getRegValue(Regs.$hp) + 1 >= getRegValue(Regs.$sp)) {
				System.err.println("\nError: Out of memory");
				return;
			}
			int bytecode = code[ip++]; // fetch
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
				for (v1 = 0; v1 < code[ip + 1]; v1++)
					memory[getRegValue(Regs.$sp) + v1] = decToByte(regs[code[ip]], (byte) (code[ip + 1] - v1 - 1));
				ip += 2;
				break;
			case SVMParser.LOADWORD:
				if (code[ip + 3] == 1)
					regs[code[ip]] = byteToDec((byte) 0, (byte) 0, (byte) 0, memory[code[ip + 1] + regs[code[ip + 2]]]);
				else
					regs[code[ip]] = byteToDec(memory[code[ip + 1] + regs[code[ip + 2]]],
							memory[code[ip + 1] + regs[code[ip + 2]] + 1],
							memory[code[ip + 1] + regs[code[ip + 2]] + 2],
							memory[code[ip + 1] + regs[code[ip + 2]] + 3]);
				ip += 4;
				break;
			case SVMParser.STOREWORD:
				for (v1 = 0; v1 < code[ip + 3]; v1++)
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

	private int getRegValue(Regs reg) {
		return regs[reg.ordinal()];
	}

	private void setRegValue(Regs reg, int value) {
		regs[reg.ordinal()] = value;
	}

	private static byte decToByte(int number, byte i) {
		return (byte) (number >> 8 * i);
	}

	// 3 2 1 0
	private static int byteToDec(byte... numbers) {
		// "& 0xFF" turns signed number to binary value, so we can shift this without be
		// aware of the sign
		return ((numbers[0] & 0xFF) << 24) | ((numbers[1] & 0xFF) << 16) | ((numbers[2] & 0xFF) << 8)
				| ((numbers[3] & 0xFF) << 0);

	}
}
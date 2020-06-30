package main;

import java.util.Random;

public class ParserPool extends ObjectPool<Parser>{

	public ParserPool(int coreSize, int maxSize) {
		super(coreSize, maxSize);
	}

	@Override
	public Parser create() {
		return new Parser(new Random().nextInt(89));
	}

}
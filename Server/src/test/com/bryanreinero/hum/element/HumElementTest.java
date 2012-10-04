package com.bryanreinero.hum.element;

import org.testng.annotations.Test;

import com.bryanreinero.hum.server.Executor;

public class HumElementTest {

	@Test(groups = { "basic" })
	public void TestRegularExpression() {

		String patternString = "regular";
		String inputString = "this is a test of regular expressions";

		Literal literal = new Literal();
		literal.setValue(patternString);
		Pattern pattern = new Pattern();
		pattern.addChild(literal);

		literal = new Literal();
		literal.setValue(inputString);
		Input input = new Input();
		input.addChild(literal);

		RegularExpression regex = new RegularExpression();
		regex.addChild(pattern);
		regex.addChild(input);

		Executor visitor = new Executor(null);
		visitor.visit(regex);

		Substitute sub = new Substitute();

		literal.setValue("REGULAR");
		sub.addChild(literal);
		regex.addChild(sub);

		visitor.visit(regex);
	}
}

package com.bryanreinero.hum.element;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

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

		HttpServletRequest req = null;
		Executor visitor = null;
		try {
			visitor = new Executor(req);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visitor.visit(regex);

		Substitute sub = new Substitute();

		literal.setValue("REGULAR");
		sub.addChild(literal);
		regex.addChild(sub);

		visitor.visit(regex);
	}
}

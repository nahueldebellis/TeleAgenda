package formatter;

import java.util.Arrays;
import java.util.List;

public class TelefonoFormatter implements Formatter<String> {

	@Override
	public List<Rule<String>> getRules() {
		return Arrays.asList(new LettersRule(), new GroupRule());
	}
}

package formatter;

import com.agenda_telefonica.contactos.Exceptions.FormatException;

public class LettersRule implements Rule<String> {

	/**
	 * Primer aproach remplazar cada uno por su valor correspondiente aplicado Regex
	 * TODO: Al crear esta cadena de reglas que aplico podria poner cada caso en una regla aparte
	 * @param phoneLetter
	 * @return
	 */
	@Override
	public String run(String inputValue) throws FormatException {
		inputValue = inputValue.toUpperCase();

		String temporalFormat = inputValue.replaceAll("A|B|C", "2");
		temporalFormat = temporalFormat.replaceAll("D|E|F", "3");
		temporalFormat = temporalFormat.replaceAll("G|H|I", "4");
		temporalFormat = temporalFormat.replaceAll("J|K|L", "5");
		temporalFormat = temporalFormat.replaceAll("M|N|O", "6");
		temporalFormat = temporalFormat.replaceAll("P|Q|R|S", "7");
		temporalFormat = temporalFormat.replaceAll("T|U|V", "8");
		temporalFormat = temporalFormat.replaceAll("W|X|Y|Z", "9");

		return temporalFormat;
	}
	
}

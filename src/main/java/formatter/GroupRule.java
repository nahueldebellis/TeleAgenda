package formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.agenda_telefonica.contactos.Exceptions.FormatException;

public class GroupRule implements Rule<String> {

	/**
	 * Creo una expresion regular para agrupar los numeros y joinearlos con un guion:
	 * <primerosCuatroNumeros>-<ultimosCuatroNumeros>
	 * @param phone
	 * @return
	 * @throws BadNumberException 
	 */
	@Override
	public String run(String inputValue) throws FormatException {
		Pattern onlyNumber = Pattern.compile("(^\\d{4})(\\d{4}$)");

		Matcher matchNumber = onlyNumber.matcher(inputValue);

		if(matchNumber.find())
			return matchNumber.group(1)+"-"+matchNumber.group(2);

		throw new FormatException("Mal numero al agrupar");
	}

}

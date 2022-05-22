package formatter;

import java.util.List;

import com.agenda_telefonica.contactos.Exceptions.FormatException;

public interface Formatter<T> {
	
	public List<Rule<T>> getRules();

	default public T format(T initialValue) throws FormatException {
		T value = initialValue;
		for(Rule<T> r: this.getRules())
			value = r.run(value);
		return value;
	}
}

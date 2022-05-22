package formatter;

import com.agenda_telefonica.contactos.Exceptions.FormatException;

/**
 * Creo una regla para que segun el tipo ejecute algun formato y que retorne el resultado.
 * Pense en implementar esto por si despues se quieren usar mas formatos para otros datos.
 * @author debel
 *
 * @param <T>
 */
public interface Rule<T> {

	public T run(T inputValue) throws FormatException;
}

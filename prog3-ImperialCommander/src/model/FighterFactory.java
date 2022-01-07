package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**@author Marta Grimaldos López, 50507753Y
 * A factory for creating Fighter objects.
 */
public class FighterFactory {
	
	/**
	 * Crea nuevos cazas dependiendo del tipo
	 *
	 * @param type the type
	 * @param mother the mother
	 * @return the fighter
	 */
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		
		try {
			Class<?> c=Class.forName("model.fighters."+type);
			Class<?>[] paramTypes = new Class[] {Ship.class};
			Constructor<?> m = c.getConstructor( paramTypes );
			Object[] arguments = new Object[] {mother};
			Fighter f = (Fighter)m.newInstance(arguments);
			return f;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}

	}
	
}

/**
 * @author prgupta
 */
package apiHelper;

public class DeleteCallManager {

	private static String IdToBeDeleted;

	public static String getIdToBeDeleted() {
		return IdToBeDeleted;
	}

	public static void setIdToBeDeleted(int idToBeDeleted) {
		IdToBeDeleted = Integer.toString(idToBeDeleted);
	}
}

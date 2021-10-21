import java.util.ArrayList;
import java.util.Arrays;

public class Valley {

    public static void main(String[] args) {
        int[] test = new int[]{1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1};
        System.out.println("Résultat final : " + Arrays.toString(valley(test)));
    }

    /*
     * La formule mathématique fournie dans l'énoncé nous dit en gros qu'il ne peut pas y avoir
     * de vallées dans une vallée ou de montagnes dans une montage. Ceci est très important pour la suite
     * ex : [1, 1, -1, -1, -1, 1, -1, 1, 1, 1, 1]
     *
     * Ma fonction opère en 2 étapes :
     *
     * D'abord elle va stocker dans slopChange les index où la pente de la montagne à changé.
     * Le début et la fin de la montagne sont considérés comme des endroits de ce type.
     * Ce tableau d'index s'affiche dans la console.
     *
     * Mtn qu'on connait toutes les index où la pente change et que chaque unité horizontale correspond
     * à une unité verticale, on peut calculer la profondeur/hauteur des vallées/sommets.
     *
     * Pour cela, j'analyse tous les groupes de 3 changements de pente ce qui correspond à un sommet ou une vallée
     * et je retiens le minimum verticale.
     *
     * Ensuite pour déterminer si ma distance verticale est un sommet ou une vallée je regarde la pente à l'index
     * du premier changement de pente de mon groupe de 3 que j'analyse son signe. Si il est positif,
     * je suis face à un sommet, si il est négatif, je suis dans une vallée.
     *
     * Après plus qu'a updater ma valeur dans "biggestValleyMountain"
     *
     * Ceci est MA solution, vous pouvez avoir une autre méthode tout aussi bien et plus !
     *
     */
    public static int[] valley(int[] array) {
        int[] biggestValleyMountain = new int[]{0, 0};
        int[] slopeChange = new int[array.length];
        int iter = 1;

        slopeChange[0] = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                slopeChange[iter] = i;
                iter++;
            }
        }
        slopeChange[iter] = array.length;
        System.out.println("Index des changements de pente : " + Arrays.toString(slopeChange));

        for (int i = 0; i < iter - 1; i++) {
            int biggest = Math.min(slopeChange[i + 1] - slopeChange[i], slopeChange[i + 2] - slopeChange[i + 1]);
            if (array[slopeChange[i]] > 0) {
                biggestValleyMountain[1] = Math.max(biggestValleyMountain[1], biggest);
            } else {
                biggestValleyMountain[0] = Math.max(biggestValleyMountain[0], biggest);
            }
        }

        return biggestValleyMountain;
    }

    // This would be a better solution. It needs less memory but doesn't work on inginious because of the package import
    public static int[] valleyWithArrayList(int[] array) {
        int[] biggestValleyMountain = new int[]{0, 0};
        ArrayList<Integer> slopeChangement = new ArrayList<>();

        slopeChangement.add(0);
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                slopeChangement.add(i);
            }
        }
        slopeChangement.add(array.length);

        for (int i = 0; i < slopeChangement.size() - 2; i++) {
            int biggest = Math.min(slopeChangement.get(i + 1) - slopeChangement.get(i), slopeChangement.get(i + 2) - slopeChangement.get(i + 1));
            if (array[slopeChangement.get(i)] > 0) {
                biggestValleyMountain[1] = Math.max(biggestValleyMountain[1], biggest);
            } else {
                biggestValleyMountain[0] = Math.max(biggestValleyMountain[0], biggest);
            }
        }

        return biggestValleyMountain;
    }

	// Solution by Bruno.
	// This works by storing the amount of consecutive equal values, from left and from right.
	// The depth/height if the slope changes from between positions `i` and `i+1` is `min(leftCount[i], rightCount[i+1])`.
	public static int[] valleyBruno(int[] array) {
        int n = array.length;
        int[] leftCount = new int[n], rightCount = new int[n];
        leftCount[0] = 1;
        rightCount[n - 1] = 1;
        for (int i = 1; i < n; ++i) {
            leftCount[i] = array[i] == array[i - 1] ? leftCount[i - 1] + 1 : 1;
        }
        for (int i = n - 1; i > 0; --i) {
            rightCount[i - 1] = array[i - 1] == array[i] ? rightCount[i] + 1 : 1;
        }
        int[] ans = new int[]{0, 0};
        for (int i = 0; i < n - 1; ++i) {
            if (array[i] != array[i + 1]) {
                int y = Math.min(leftCount[i], rightCount[i + 1]);
                int index = array[i] == -1 ? 0 : 1;
                ans[index] = Math.max(ans[index], y);
            }
        }
        return ans;
    }
	
	/*
    * On va parcourir la liste UNE SEULE FOIS, et stocker son status en temps réel dans l'array status.
    * En initialisant, on setup le statut sur la premiere slope de l'array que l'on reçoit en entrée,
    * afin de ne pas avoir à gérer le cas plus tard
    * À chaque étape, on regarde si la pente est toujours la même que celle stockée dans le status, et on la change au besoin,
    * puis on incrémente la succession de montée / descente correspondante dans l'array status.
    * Ensuite, on vérifie si, en fonction de si on est sur une montée ou une descente,
    * on obtient une vallée / montagne plus grande que celles déjà obtenues.
     */
    public static int[] valleyOneLoop (int[] slopes){
        int[] result = new int[2];
        int[] status = {slopes[0], 0, 0, slopes[0]};
        // {-1 : valley, 1: mountain; last sucession neg; last succession pos; current searched (-1 pente, 1 montée)}
        for (int i: slopes) {
            if (status[3] == i) { // Si la pente/ montée continue
                if (i == -1)
                    status[1] += 1; // Incrémente la valeur de la pente
                else
                    status[2] += 1; // Incrémente la valeur de la montée
            } else {
                status[3] = i; // Si la pente / montée change
                if (i == -1) {
                    status[1] = 1; // reset de la valeur de la dernière pente, vu que je commence à descendre
                    status[0] = 1; // je regarde maintenant pour une fin de montagne (on la descend)
                } else {
                    status[2] = 1; // reset de la valeur de la dernière montée, vu que je commence à monter
                    status[0] = -1; // je regarde maintenant pour une fin de vallée (on la monte)
                }
            }
            if (status[0] == -1)
                result[0] = Math.max(result[0], Math.min(status[1], status[2]));
            else
                result[1] = Math.max(result[1], Math.min(status[1], status[2]));
        }
        return result;
    }
}

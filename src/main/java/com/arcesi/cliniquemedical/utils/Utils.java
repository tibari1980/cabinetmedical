package com.arcesi.cliniquemedical.utils;

import java.util.Random;

public class Utils {

	/**
	 * 
	 * @param borneInf nombre inférieur
	 * @param borneSup nombre supérieur
	 * @return
	 */
	 public static int genererInt(int borneInf, int borneSup) {
		Random random = new Random();
		int nb;
		nb = borneInf + random.nextInt(borneSup - borneInf);
		return nb;
	}
}

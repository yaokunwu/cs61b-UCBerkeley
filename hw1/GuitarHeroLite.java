import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
//    private static final double CONCERT_A = 440.0;
//    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
//        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
//        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        synthesizer.GuitarString stringall[] = new synthesizer.GuitarString[37];
        for (int i = 0; i < keyboard.length(); i += 1) {
            double frequency = 440.0 * Math.pow(2.0,(i - 24.0)/12.0);
            stringall[i] = new synthesizer.GuitarString(frequency);
        }


        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int ind = keyboard.indexOf(key);
                if (ind == -1){
                    continue;
                }
                stringall[ind].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString item : stringall) {
                sample += item.sample();
            }
//            double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString item : stringall) {
                item.tic();
            }
//            stringA.tic();
//            stringC.tic();
        }

//        while (true) {
//
//            /* check if the user has typed a key; if so, process it */
//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                if (key == 'a') {
//                    stringA.pluck();
//                } else if (key == 'c') {
//                    stringC.pluck();
//                }
//            }
//
//        /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();
//
//        /* play the sample on standard audio */
//            StdAudio.play(sample);
//
//        /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
//        }
    }
}


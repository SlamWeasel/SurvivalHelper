package de.SlamWeasel.SurvivalHelper.util;

public class Util
{
    public static String[] getStringArray(Object[] IN)
    {
        String[] OUT = new String[IN.length];
        for(int i = 0; i < IN.length; i++)
        {
            OUT[i] = IN[i].toString();
        }
        return OUT;
    }
}


public class ManageAtm {

    public static void updateDenomination(int amount, int denomination, ManageDenomination Denomination){
        if(amount==2000){
                Denomination.setTwoThousand(Denomination.getTwoThousand()+denomination);
        }
        else if(amount==500){
                Denomination.setFiveHundred(Denomination.getFiveHundred()+denomination);
        }
        else if(amount==100){
                Denomination.setOneHundred(Denomination.getOneHundred()+denomination);
        }
    }

    public static int reduceDenomination(int amount, int denomination, ManageDenomination Denomination){
        int flag1=0, flag2=0;
        if(amount==2000){
            if(Denomination.getTwoThousand()>0){
                Denomination.setTwoThousand(Denomination.getTwoThousand()-denomination);
                return 1;
            }
            else if(Denomination.getFiveHundred()>0){
                flag1=1;
                Denomination.setFiveHundred(Denomination.getFiveHundred()-denomination);
            }
            else if(Denomination.getOneHundred()>0){
                flag2=1;
                Denomination.setOneHundred(Denomination.getOneHundred()-denomination);
            }
        }
        else if(amount==500){
            if(Denomination.getFiveHundred()>0){
                if(flag1==0){
                    Denomination.setFiveHundred(Denomination.getFiveHundred()-denomination);
                    return 1;
                }
            }
            else if(Denomination.getOneHundred()>0)
                if(flag2==0)
                Denomination.setOneHundred(Denomination.getOneHundred()-denomination);
        }
        else if(amount==100){
            if(Denomination.getOneHundred()>0){
                if(flag2==0){
                Denomination.setOneHundred(Denomination.getOneHundred()-denomination);
                return 1;}
            }
        }
        return -1;
    }

    public static void updateDepositingAmount(AtmDB atmDatabase, ManageDenomination Denomination){
        int depositingAmount=Denomination.getTwoThousand()*2000+Denomination.getFiveHundred()*500+Denomination.getOneHundred()*100;
        atmDatabase.setDeposingAmount(depositingAmount);
        atmDatabase.setBalaceAmount(atmDatabase.getDeposingAmount());
    }

    public static void updateWithdraw(AtmDB atmDatabase, int withdrawAmount){
        atmDatabase.setWithdrawAmount(withdrawAmount);
        atmDatabase.setBalaceAmount(atmDatabase.getBalaceAmount()-withdrawAmount);
    }

    public static int[] dispensingDenomination(int[] notes, int withdrawAmount){
        int[] noteCounter=new int[notes.length];
        for(int i=0;i<notes.length;i++){
            if(withdrawAmount>=notes[i]){
                noteCounter[i]=withdrawAmount/notes[i];
                withdrawAmount=withdrawAmount-noteCounter[i]*notes[i];
            }
        }
        return noteCounter;
    }

}










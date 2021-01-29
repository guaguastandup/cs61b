public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y){
        //System.out.println("x:"+x+",y:"+y);
        return (x==y+1||y==x+1);
    }
}

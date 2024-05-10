public class Main
{
    public static void main(String[] args)
    {
        Pair[] array = new Pair[]
        {
            new Pair(1, 2),
            new Pair(2, 4),
            new Pair(4, 6),
            new Pair(2, 10),
            new Pair(4, 8),
            new Pair(6, 3),
            new Pair(1, 3),
            new Pair(3, 5),
            new Pair(5, 8),
            new Pair(7, 5),
            new Pair(7, 9),
            new Pair(9, 4),
            new Pair(9, 10),
        };

        PartiallyOrderedSet partiallyOrderedSet = new PartiallyOrderedSet();
        boolean res = partiallyOrderedSet.Init(array);

        if(!res)
        {
            System.out.println("Нарушение иррефлексивности!");
            return;
        }

        res = partiallyOrderedSet.Sort();

        if(!res)
        {
            System.out.println("Не удалось выполнить сортировку!");
            return;
        }

        partiallyOrderedSet.Print();
    }

}
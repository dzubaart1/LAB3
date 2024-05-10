public class Item
{
    public int InputsCount = 0;
    public int Value;
    public Item NextItem;
    public SubItem StartSubItem;

    public Item(int value, Item next)
    {
        Value = value;
        NextItem = next;
    }

    public void DecreaseInputsCount()
    {
        if(InputsCount - 1 < 0)
        {
            return;
        }

        InputsCount--;
    }
}

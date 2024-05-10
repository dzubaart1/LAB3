public class SubItem
{
    public SubItem NextSubItem;
    public Item CurrentItem;

    public SubItem(Item item)
    {
        CurrentItem = item;
    }
}

public class PartiallyOrderedSet
{
    private Item _start; // Старт обычного множества

    private Item _sortedStart; // Старт сортированного множества

    // Инициализирующий конструктор
    public PartiallyOrderedSet()
    {
        _start = null;
        _sortedStart = null;
    }

    // Метод инициализации
    public boolean Init(Pair[] array)
    {
        Item itemA, itemB;
        SubItem subItem;
        int a, b;

        // Проходимся по массиву
        for (int i = 0; i < array.length; i++)
        {
            a = array[i].A;
            b = array[i].B;

            // Если пара эл-тов равны, то false
            if(a == b)
            {
                return false;
            }

            // Ищем, есть ли уже эл-т b
            itemB = GetItemByValue(b);
            // Если нет, то создаем и пихаем в начало
            if(itemB == null)
            {
                itemB = new Item(b, _start);
                _start = itemB;
            }
            // Добавляем InputsCount + 1
            itemB.InputsCount++;
            // Инициализируем SubItem
            subItem = new SubItem(itemB);

            // Ищем, есть ли уже эл-т a
            itemA = GetItemByValue(a);
            // Если нет, то создаем и пихаем в начало
            if(itemA == null)
            {
                _start = new Item(a, _start);
                _start.StartSubItem = subItem;
            }
            else
            {
                subItem.NextSubItem = itemA.StartSubItem;
                itemA.StartSubItem = subItem;
            }
        }

        return true;
    }

    // Метод сортировки
    public boolean Sort()
    {
        // Пока есть эл-ты с нулем InputsCount
        Item currentItem = GetItemWithZeroInputsCount();

        while (currentItem != null)
        {
            // Уменьшаем все InputsCount у SubItem
            DecreaseSubItems(currentItem);

            // Добавляем в сортированный список
            AppendToSortedList(currentItem);
            currentItem = GetItemWithZeroInputsCount();
        }

        return _start == null;
    }

    // Вывод моего творчества
    public void Print()
    {
        Item sortedItem = _sortedStart;

        while (sortedItem != null)
        {
            System.out.println(sortedItem.Value + " ");
            sortedItem = sortedItem.NextItem;
        }
    }

    // Возвращение эл-та с нулем InputsCount
    private Item GetItemWithZeroInputsCount()
    {
        // Если нет начала, то null
        if(_start == null)
        {
            return null;
        }

        // Если голова подходит, то удаляем голову и возвращаем ее
        if(_start.InputsCount == 0)
        {
            Item temp = _start;
            _start = _start.NextItem;
            temp.NextItem = null;
            return temp;
        }


        // Проходимся по осташемуся списку и делаем тоже самое
        Item prevItem = _start;
        Item currentItem = _start.NextItem;
        while (currentItem != null)
        {
            if(currentItem.InputsCount == 0)
            {
                prevItem.NextItem = prevItem.NextItem.NextItem;
                currentItem.NextItem = null;
                return currentItem;
            }

            prevItem = currentItem;
            currentItem = currentItem.NextItem;
        }

        return null;
    }

    // Возвращение эл-т по значению
    private Item GetItemByValue(int value)
    {
        Item currentItem = _start;

        while (currentItem != null)
        {
            if(currentItem.Value == value)
            {
                return currentItem;
            }

            currentItem = currentItem.NextItem;
        }

        return null;
    }

    // Уменьшает InputsCount во всех SubItem в Item
    private void DecreaseSubItems(Item item)
    {
        SubItem currentSubItem = item.StartSubItem;

        while (currentSubItem != null)
        {
            currentSubItem.CurrentItem.DecreaseInputsCount();
            currentSubItem = currentSubItem.NextSubItem;
        }
    }

    // Добавляем в сортированный спиоск в конец
    private void AppendToSortedList(Item item)
    {
        if(_sortedStart == null)
        {
            _sortedStart = item;
            return;
        }

        Item prevItem = null;
        Item currentItem = _sortedStart;

        while (currentItem != null)
        {
            prevItem = currentItem;
            currentItem = currentItem.NextItem;
        }

        prevItem.NextItem = item;
    }
}

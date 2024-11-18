#!/bin.bash

AD = "addressbook.txt"

create_book(){
    touch "$AD"
    echo "address  book created"
}

view_book(){
    cat "$AD"
}

insert(){
    echo "Enter name"
    read name
    echo "Enter number"
    read no
    echo "Enter Address"
    read location

    echo "$name | $no | $location">>"$AD"
    echo "Record inserted"
}

delete_rec(){
    echo "Enter name of record to be deleted"
    read name
    grep -v "^$name |" "$AD">>"${AD}.tmp"
    mv "${AD}.tmp" >> "$AD"
    echo "record deleted"
}

modify(){
    echo "Enter name to be modified"
    read name
    grep -v "^$name |" "$AD">>"${AD}.tmp"
    mv "${AD}.tmp">>"$AD"

    echo "Enter new number"
    read no

    echo "Enter new location"
    read loc
    echo "$name | $no | $loc" >> "$AD"

}

while true;do
    echo "1) create book"
    echo "2) View book"
    echo "3) insert in book"
    echo "4) delete rec from book"
    echo "5) modify book"
    echo "6) exit"

    read -p "Choose an option" option
    case $option in
        1)create_book;;
        2)view_book;;
        3)insert;;
        4)delete_rec;;
        5)modify;;
        6)exit 0;;
        *) echo "Invalid choice"
    esac
done

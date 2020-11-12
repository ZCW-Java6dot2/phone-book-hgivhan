package com.zipcodewilmington.phonebook;

import java.util.*;
//import java.util.HashMap;


/**
 * Created by leon on 1/23/18.
 * Made WAY better by kristofer 6/16/20
 */
public class PhoneBook {

    private final Map<String, List<String>> phonebook;

    public PhoneBook(Map<String, List<String>> map) {
        this.phonebook = map;
    }
    // this will set all key values from map to phonebook

    public PhoneBook() {
        phonebook = new LinkedHashMap<String, List<String>>(); // so you can return map in order, less efficitent for doing looups
// set private variable to equal a new empty map
// cannot initiate an abstract member of class, use hash to extend map
//Map is base of class, various ways to implement it, one way is Hash
    }

    public void add(String name, String phoneNumber) {
//check for key, if there, just add #. If not there, put name as key and List value with phone
 if (phonebook.containsKey(name)){
     List<String>updatedNumbers = phonebook.get(name); // putting object into key (like something int array index)
     updatedNumbers.add(phoneNumber); // you take list, update, then put it back in
     phonebook.replace(name,updatedNumbers); // if you use put() that means there is no key. Since there is a key, we use replace to update the values
 }else{
     phonebook.put(name, Collections.singletonList(phoneNumber)); //singleton list immutable, not real issue bc we really make new list every time we update
 }


//we want all keys to be unique, so we want to check if the key(name)
// is already here. if it does, just add phone to list assoc with that key
// person with home # may exist, so then we will just want to add additional # like mobile to list
//add phone # as value, but it's gotta be in list bc that is the what hte value is set to
    }

    public void addAll(String name, String... phoneNumbers) {
        if (phonebook.containsKey(name)){
            List<String>updatedNumbers = phonebook.get(name);
            updatedNumbers.addAll(Arrays.asList(phoneNumbers)); // addAll requires collection, so we turn it to list object
            phonebook.replace(name,updatedNumbers);//there is a key, so we use replace()
        }else{
            phonebook.put(name, Arrays.asList(phoneNumbers));
        }
    }

    public void remove(String name) {
        phonebook.remove(name);
    }
    //remove removes mapping from key if it exists

    public Boolean hasEntry(String name) {
        return phonebook.containsKey(name);
    }

    public List<String> lookup(String name) {
       return phonebook.get(name);
    } // return all #s associated with key once we check for the key, get method returns null if no mapping for key

    public String reverseLookup(String phoneNumber)  {
        Set<String> allKeys = phonebook.keySet(); // a set is a list that has all unique values, no duplicates
        for (String key : allKeys){
            List<String> values = phonebook.get(key);//get values assoc with particular key, if we find the value we want, we can return the name assoc with it
        if(values.contains(phoneNumber)) {
            return key; // if phone doesn't match with values, error will say sth like all paths don't return value
        }
        }
        return "";
    }

    public List<String> getAllContactNames() {
        List<String> contactNames = new ArrayList<String>(); // won't accept list, it's abstract  cant set new one, but can set new array list.
        for (String key : phonebook.keySet()){
            contactNames.add(key);
            //making list, looping thru all values in map and adding each key to list till it's full and return that

        }
        return contactNames;
        // other option: return Arrays.asList((String[])phonebook.keySet().toArray()); //wrap everything in
    } //lots of conversions. phonebook.keySet() returns a set. we need to get it to a list of Strings
    // Arrays.asList converts array to list.
    //in parenthesis, array of strings, arrays.aslist works to make it list of string.

    public Map<String, List<String>> getMap() {
        return phonebook;
    }
}
//A map reduces the amount of time you have to loop thru a list to find something
//A key has a value associated to a number
//Can make key of Kyle with all his phone numbers right there. Kyles name and #s = 2 pieces of data
//single array would be hard to do this efficiently. Associative property is better
// You can delete, add, and change things and check if key is there or not
//empty map has no keys or values. if you want to create it
//Key as first argument, value as second argument
//map versus hashmap. hashmaps have a lot of built in methods
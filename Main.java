interface IFirst {
    void sayTarget();
}

class First implements IFirst {
    public void sayTarget() {
        System.out.println("one");
    }
}

class Second implements IFirst {
    public void sayTarget() {
        System.out.println("two");
    }
}

class FirstFactory implements Factory {
    public IFirst create(String type) {
        switch (type) {
            case "one": return new First();
            case "two": return new Second();
            default: return null;
        }
    }
}

interface IThird {
    void sayTarget();
}

class Third implements IThird {
    public void sayTarget() {
        System.out.println("three");
    }
}

class Fourth implements IThird {
    public void sayTarget() {
        System.out.println("four");
    }
}

class ThirdFactory implements Factory {
    public IThird create(String type) {
        switch (type) {
            case "three": return new Third();
            case "four": return new Fourth();
            default: return null;
        }
    }
}

interface Factory<T> {
    T create(String type);
}

class AbstractFactory implements Factory {
    public Factory create(String type) {
        switch (type) {
            case "three" : return new ThirdFactory();
            case "one" : return new FirstFactory();
            default: return null;
        }
    }
}

class Test {
    public static void main(String[] args ){
        Factory<Factory> aFactory = new AbstractFactory();
        Factory<IFirst> firstFactory = aFactory.create("one");
        Factory<IThird> thirdFactory = aFactory.create("three");

        IFirst one = firstFactory.create("one");
        IFirst two = firstFactory.create("two");

        IThird three = thirdFactory.create("three");
        IThird four = thirdFactory.create("four");


        one.sayTarget();
        two.sayTarget();

        three.sayTarget();
        four.sayTarget();
    }
}
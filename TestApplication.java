import java.util.HashMap;
class DigitalLibrarySystem {
    private Catalog catalog;
    private MemberManager memberManager;
    private Screen screen;
    private Book currentBook;
    private Member currentMember;

    public DigitalLibrarySystem() {
        memberManager = new MemberManager();
        catalog = new Catalog();
        screen = new Screen();
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public MemberManager getMemberManager() {
        return memberManager;
    }

    public Screen getScreen() {
        return screen;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book book) {
        currentBook = book;
        screen.showBook(book);
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member member) {
        currentMember = member;
        screen.showMember(member);
    }
}
class Catalog {
    private HashMap<Integer, Book> bookCollection;

    public Catalog() {
        bookCollection = new HashMap<>();
    }

    public boolean insertBook(int id, String description, String title) {
        if (bookCollection.containsKey(id)) {
            return false;
        }
        Book book = new Book(id, description, title);
        bookCollection.put(id, book);
        return true;
    }

    public boolean insertBook(Book book) {
        if (bookCollection.containsKey(book.getId())) {
            return false;
        }
        bookCollection.put(book.getId(), book);
        return true;
    }

    public boolean deleteBook(Book book) {
        return deleteBook(book.getId());
    }

    public boolean deleteBook(int id) {
        if (!bookCollection.containsKey(id)) {
            return false;
        }
        bookCollection.remove(id);
        return true;
    }

    public Book searchBook(int id) {
        return bookCollection.get(id);
    }
}
class MemberManager {
    private HashMap<Integer, Member> memberDatabase;

    public MemberManager() {
        memberDatabase = new HashMap<>();
    }

    public boolean registerMember(int id, String info, String name) {
        if (memberDatabase.containsKey(id)) {
            return false;
        }
        Member member = new Member(id, info, name);
        memberDatabase.put(id, member);
        return true;
    }

    public boolean registerMember(Member member) {
        if (memberDatabase.containsKey(member.getId())) {
            return false;
        }
        memberDatabase.put(member.getId(), member);
        return true;
    }

    public boolean removeMember(Member member) {
        return removeMember(member.getId());
    }

    public boolean removeMember(int id) {
        if (!memberDatabase.containsKey(id)) {
            return false;
        }
        memberDatabase.remove(id);
        return true;
    }

    public Member findMember(int id) {
        return memberDatabase.get(id);
    }
}
class Screen {
    private Book currentBook;
    private Member currentMember;
    private int currentPage = 0;

    public void showMember(Member member) {
        currentMember = member;
        updateMemberName();
    }

    public void showBook(Book book) {
        currentPage = 0;
        currentBook = book;
        updateTitle();
        updateDescription();
        updatePage();
    }

    public void nextPage() {
        currentPage++;
        System.out.println("Moved forward to page " + currentPage + " of the book titled: " + currentBook.getTitle());
        updatePage();
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            System.out.println("Moved back to page " + currentPage + " of the book titled: " + currentBook.getTitle());
            updatePage();
        } else {
            System.out.println("You are already on the first page!");
        }
    }

    private void updateMemberName() {
        System.out.println("Displayed member name: " + currentMember.getName());
    }

    private void updateTitle() {
        System.out.println("Book title refreshed: " + currentBook.getTitle());
    }

    private void updateDescription() {
        System.out.println("Book description refreshed: " + currentBook.getDetails());
    }

    private void updatePage() {
        System.out.println("Page " + currentPage + " updated on the screen.");
    }
}
class Book {
    private int id;
    private String description;
    private String title;

    public Book(int id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
class Member {
    private int id;
    private String name;
    private String info;

    public Member(int id, String info, String name) {
        this.id = id;
        this.info = info;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return info;
    }

    public String getName() {
        return name;
    }
}
public class TestApplication {
    public static void main(String[] args) {
        DigitalLibrarySystem librarySystem = new DigitalLibrarySystem();

        Book book1 = new Book(101, "Covers basics of Data Structures", "Data Structures");
        Book book2 = new Book(102, "Introduction to Algorithms", "Algorithms");

        librarySystem.getCatalog().insertBook(book1);
        librarySystem.getCatalog().insertBook(book2);

        Member member1 = new Member(1, "Student", "Arun");
        Member member2 = new Member(2, "Researcher", "Sneha");

        librarySystem.getMemberManager().registerMember(member1);
        librarySystem.getMemberManager().registerMember(member2);

        librarySystem.setCurrentBook(book2);
        librarySystem.setCurrentMember(member1);

        librarySystem.getScreen().nextPage();
        librarySystem.getScreen().nextPage();
        librarySystem.getScreen().previousPage();
    }
}

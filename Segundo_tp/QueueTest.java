package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
  private Queue queue;
  private final String something = "Something";
  private final String first = "First";
  private final String second = "Second";

  @BeforeEach
  void init() {
    this.queue = new Queue();
  }

  @Test
  void isEmptyWhenCreated() {
    assertTrue(queue.isEmpty());
  }

  @Test
  void isNotEmptyAfterAdd() {
    addSomethingToQueue();
    assertFalse(queue.isEmpty());
  }

  @Test
  void addedIsAtHead() {
    addSomethingToQueue();
    assertEquals(something, queue.head());
  }


  @Test
  void takeRemovesElement() {
    addSomethingToQueue();
    queue.take();
    assertTrue(queue.isEmpty());
  }

  @Test
  void takeReturnsAddedElement() {
    addSomethingToQueue();
    assertEquals(something, queue.take());
  }

  @Test

  void behavesAsFIFO() {
    addToQueueInOrder();
    assertEquals(first, queue.take());
    assertEquals(second, queue.take());
    assertTrue(queue.isEmpty());

  }

  @Test
  void headReturnsFirstElement() {
    addToQueueInOrder();
    assertEquals(first, queue.head());
  }

  @Test
  void headDoesNotRemove() {
    addSomethingToQueue();
    assertSize(1);
    queue.head();
    assertSize(1);
  }


  @Test
  void sizeEqualsElements() {
    addToQueueInOrder();
    assertSize(2);
  }

  @Test
  void takeOnEmptyThrows() {
    assertThrows(Error.class, () -> queue.take());
  }


  @Test
  void headOnEmptyThrows() {
    assertThrows(Error.class, () -> queue.head());
  }

  @Test
  void takeOnDepletedThrows() {
    addSomethingToQueue();
    queue.take();
    assertThrows(Error.class, () -> queue.take());
  }
  @Test
  void canAddNullElement() {
    addNull();
  }
  @Test
  void addingAndTakingNull() {
    addNull();
    assertNull(queue.take());
  }
  @Test
  void sizeIncrementsWhenAddingElements() {
    assertEquals(0, queue.size());
    addSomethingToQueue();
    assertEquals(1, queue.size());
  }

  @Test
  void canAddDifferentDataTypes() {
    queue.add(123);
    queue.add(true);
  }

  @Test
  void queueSizeDecrementsWhenTaking() {
    addSomethingToQueue();
    assertEquals(1, queue.size());

    queue.take();
    assertEquals(0, queue.size());
  }

  private void addNull() {
    queue.add(null);
  }


  private void addSomethingToQueue() {
    queue.add(something);
  }

  private void addToQueueInOrder() {
    queue.add(first);
    queue.add(second);
  }

  private void assertSize(int size) {
    assertEquals(size, queue.size());
  }
}

package pt.unl.fct.iadi.novaevents2.controller

class ClubNotFoundException(message: String = "Club not found") : NoSuchElementException(message)
class EventNotFoundException(message: String = "Event not found") : NoSuchElementException(message)
class EventBadlyCreatedException(message: String = "The information of the " +
        "event that you are trying to create is badly inputted."): RuntimeException(message)
class DuplicateEventNameException(message: String = "Duplicated Event Name") : RuntimeException(message)
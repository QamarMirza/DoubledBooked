(ns clojure.doubleBooked
	(:gen-class))

;; --- Assumptions ---
;; start/end times are unix time stamp
;; start is always less than end (all intervals are valid)
;; meeting id are unique

(defn isConflict
  [intialMeeting conflictMeeting]
  (and 
    (< (get intialMeeting :start ) (get conflictMeeting :end ))
    (< (get conflictMeeting :start ) (get intialMeeting :end ))  
  )
)

(defn doubleBooked 
    [meetings]
    
    (def length (count meetings))
    
    (if (< length 2) 
        ()
        (do
          (for [x (range 0 length) 
                y (range (+ x 1) length)
                :when(isConflict (get meetings x) (get meetings y) )]
               [(get (get meetings x) :id) (get (get meetings y) :id)]
          )
        )
    )
)

;; Thursday January 10, 10am - 11am
(def meeting1 {:id "m1" :start 1515578400 :end 1515582000}) 

;; Thursday January 10, 10am - 12pm
(def meeting2 {:id "m2" :start 1515578400 :end 1515585600})

;; Thursday January 10, 12pm - 2pm
(def meeting3 {:id "m3" :start 1515585600 :end 1515592800})

;; Thursday January 10, 1030am - 130pm
(def meeting4 {:id "m4" :start 1515580200 :end 1515591000})

;; Thursday January 11, 1030am - 130pm
(def meeting5 {:id "m5" :start 1515666600 :end 1515677400})

(doubleBooked [meeting1 meeting2 meeting3 meeting4 meeting5])
(doubleBooked [meeting2 meeting3 meeting5])
(doubleBooked [meeting1 ])
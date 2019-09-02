(in-package :hlu)

(deflayer font (t)
  (lambda (msg fn out)
    (decoder msg out
      (let ((ver :uint8 "version"))
        (if (= ver 1)
            (let ((type :uint8 "type"))
              (if (zerop type)
                  :raw :all (strcat fn ".ttf")
                  (error "Invalid type in font")))
            (error "Invalid version in font")))))
  (lambda (fn out in)
    (encoder in out
      (let ((ver :uint8))
        (if (= ver 1)
            (let ((type :uint8))
              (if (zerop type)
                  :raw (strcat fn ".ttf")
                  (error "Invalid type in font")))
            (error "Invalid version in font"))))))

namespace srv;

using { db.Comment, db.User } from '../db/database';

service review {

    entity Comments as projection on Comment;
    entity Users as projection on User;

}


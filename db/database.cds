using{ cuid } from '@sap/cds/common';
namespace db;

entity Comment : cuid {
    title: String(64);
    body: String(1024);
    evaluation: Integer @assert.range: [ 1, 5 ];
    writtenBy: Association to one User;
    refferedTo: Association to one User;
}

entity User : cuid {
    username: String(64);
    email: String(128);
    password: String(256);
    averageRating: Double();
    role: String enum {
      admin = 'admin';
      user = 'user';
    };
    writtenByMe: Composition of many Comment on writtenByMe.writtenBy=$self;
    writtenAboutMe: Composition of many Comment on writtenAboutMe.refferedTo=$self;
}